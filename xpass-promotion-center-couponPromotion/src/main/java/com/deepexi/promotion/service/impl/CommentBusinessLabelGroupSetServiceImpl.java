package com.deepexi.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.CommentBusinessLabelGroupSetDAO;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentTemplateTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.mapper.CommentBusinessLabelGroupSetMapper;
import com.deepexi.promotion.service.CommentBusinessLabelGroupSetService;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author admin
 */
@Service
@Slf4j
public class CommentBusinessLabelGroupSetServiceImpl
        extends ServiceImpl<CommentBusinessLabelGroupSetMapper, CommentBusinessLabelGroupSetDO>
        implements CommentBusinessLabelGroupSetService {

    @Resource
    private CommentBusinessLabelGroupSetDAO commentBusinessLabelGroupSetDAO;


    @Override
    public boolean batchCreate(CommentTemplateAddGroupDTO dto) {
        Optional.ofNullable(dto).orElseThrow(() -> new ApplicationException(ResultEnum.CREATE_ERROR));
        if (CollectionUtil.isEmpty(dto.getLabelGroupIdList())) {
            return false;
        }
        List<CommentBusinessLabelGroupSetDO> collect = dto.getLabelGroupIdList().stream()
                .map(item -> CommentBusinessLabelGroupSetDO.builder()
                        .businessModelConnectId(dto.getBusinessModelConnectId())
                        .labelGroupId(item)
                        .type(dto.getType())
                        .build())
                .collect(Collectors.toList());
        log.info("批量创建标签组关联：{}", collect);
        commentBusinessLabelGroupSetDAO.saveBatch(collect);
        //评价模板才操作缓存 回评不操作
        if (CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode().equals(dto.getType())) {
            RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, dto.getBusinessId());
        }
        return true;
    }

    @Override
    public boolean deleteByVO(CommentTemplateDeleteGroupVO vo) {
        int n = commentBusinessLabelGroupSetDAO.deleteByVO(vo);
        return n > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resetByCommentTemplateAddGroupDTO(CommentTemplateAddGroupDTO dto) {
        log.info("重新创建：{}", dto);
        //只能删除自己的
        CommentTemplateDeleteGroupVO vo = new CommentTemplateDeleteGroupVO();
        vo.setBusinessModelConnectId(dto.getBusinessModelConnectId());
        vo.setType(dto.getType());
        commentBusinessLabelGroupSetDAO.deleteByVO(vo);
        return this.batchCreate(dto);
    }

    @Override
    public List<CommentBusinessLabelGroupSetDTO> findListByConnectIds(CommentBusinessTemplateSetQuery query) {
        return ObjectCloneUtils.convertList(commentBusinessLabelGroupSetDAO.findListByConnectIds(query), CommentBusinessLabelGroupSetDTO.class, CloneDirection.OPPOSITE);
    }
}