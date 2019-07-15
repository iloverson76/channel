package com.deepexi.promotion.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.CommentBusinessStarSetDAO;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentTemplateTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deepexi.promotion.service.CommentBusinessStarSetService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhangyanping
 * @date 2019/5/24 15:44
 */
@Service
@Slf4j
public class CommentBusinessStarSetServiceImpl implements CommentBusinessStarSetService {


    @Autowired
    private CommentBusinessStarSetDAO commentBusinessStarSetDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createCommentBusinessStarSet(CommentBusinessStarSetDTO dto) {
        return commentBusinessStarSetDAO.save(dto.clone(CommentBusinessStarSetDO.class));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByCondition(CommentBusinessStarSetDeleteVO vo) {
        int n = commentBusinessStarSetDAO.deleteByCondition(vo);
        return n > 0;
    }

    @Override
    public List<CommentBusinessStarSetDTO> findListByConnectIds(CommentBusinessTemplateSetQuery query) {
        if (query.getConnectIds() == null || query.getConnectIds().length <= 0) {
            return new ArrayList<>();
        }
        QueryWrapper<CommentBusinessStarSetDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", query.getType());
        queryWrapper.in("business_model_connect_id", query.getConnectIds());
        List<CommentBusinessStarSetDO> list = commentBusinessStarSetDAO.list(queryWrapper);
        return ObjectCloneUtils.convertList(list, CommentBusinessStarSetDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public boolean saveList(CommentBusinessStarSetInputDTO dto) {

        Optional.ofNullable(dto).orElseThrow(() -> new ApplicationException(ResultEnum.CREATE_ERROR));
        if (CollectionUtil.isEmpty(dto.getStarIdList())) {
            return false;
        }
        CommentBusinessStarSetDeleteVO deleteVO = new CommentBusinessStarSetDeleteVO();
        deleteVO.setBusinessModelConnectId(dto.getBusinessModelConnectId());
        deleteVO.setType(dto.getType());
        commentBusinessStarSetDAO.deleteByCondition(deleteVO);
        List<CommentBusinessStarSetDO> collect = dto.getStarIdList().stream()
                .map(item -> CommentBusinessStarSetDO.builder()
                        .businessModelConnectId(dto.getBusinessModelConnectId())
                        .starTemplateId(item)
                        .type(dto.getType())
                        .build())
                .collect(Collectors.toList());
        log.info("批量创建星级关联：{}", collect);
        boolean flag = commentBusinessStarSetDAO.saveBatch(collect);
        if (CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode().equals(dto.getType())) {
            RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, dto.getBusinessId());
        }
        return flag;
    }
}