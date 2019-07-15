package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentDetailCheckDAO;
import com.deepexi.promotion.dao.CommentDetailDAO;
import com.deepexi.promotion.domain.CommentDetailCheckDO;
import com.deepexi.promotion.domain.CommentDetailDO;
import com.deepexi.promotion.domain.CommentDetailDTO;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.promotion.mapper.CommentDetailCheckMapper;
import com.deepexi.promotion.service.CommentDetailCheckService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Service
@Slf4j
public class CommentDetailCheckServiceImpl
        extends ServiceImpl<CommentDetailCheckMapper, CommentDetailCheckDO> implements CommentDetailCheckService {

    @Resource
    private CommentDetailCheckDAO commentDetailCheckDAO;

    @Resource
    private CommentDetailDAO commentDetailDAO;

    @Resource
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBatch(List<CommentDetailDTO> detailDTOList) {
        if (CollectionUtil.isEmpty(detailDTOList)) {
            return false;
        }
        List<CommentDetailDO> commentDetailVOList = ObjectCloneUtils.convertList(detailDTOList, CommentDetailDO.class);
        if (!commentDetailDAO.updateBatch(commentDetailVOList)) {
            return false;
        }
        List<CommentDetailCheckDO> commentDetailCheckDOList = commentDetailVOList.stream()
                .map(item -> {
                    CommentDetailCheckDO detailCheckDO = CommentDetailCheckDO.builder()
                            .checkStatus(item.getCheckStatus()).commentDetailId(item.getId())
                            // todo 暂时这样写 后续接入认证 后修改
                            .checkUserId("").checkUserName(appRuntimeEnv.getUsername())
                            .build();
                    detailCheckDO.setTenantId(appRuntimeEnv.getTenantId());
                    return detailCheckDO;
                }).collect(Collectors.toList());
        commentDetailCheckDAO.insertBatch(commentDetailCheckDOList);
        return true;
    }
}