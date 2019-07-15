package com.deepexi.promotion.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.CommentBusinessModelConnectDAO;
import com.deepexi.promotion.dao.CommentModelDAO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectDO;
import com.deepexi.promotion.domain.CommentModelQuery;
import com.deepexi.promotion.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deepexi.promotion.domain.CommentModelDO;
import com.deepexi.promotion.domain.CommentModelDTO;
import com.deepexi.promotion.service.CommentModelService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;

/**
 * @author zhangyanping
 * @date 2019/5/22 11:52
 */
@Service
@Slf4j
public class CommentModelServiceImpl implements CommentModelService {


    @Autowired
    private CommentModelDAO commentModelDAO;
    @Autowired
    private CommentBusinessModelConnectDAO commentBusinessModelConnectDAO;

    @Override
    public List<CommentModelDTO> findCommentModelPage(CommentModelQuery query) {
        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<CommentModelDO> pageList = commentModelDAO.findCommentModelPage(query);
        return ObjectCloneUtils.convertList(pageList, CommentModelDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public CommentModelDTO getCommentModel(Long id) {
        CommentModelDO result = commentModelDAO.getCommentModel(id);
        if (result != null) {
            return result.clone(CommentModelDTO.class);
        }
        return null;
    }

    @Override
    public Boolean updateCommentModel(Long id, CommentModelDTO dto) {
        dto.setId(id);
        boolean flag = commentModelDAO.updateCommentModel(dto.clone(CommentModelDO.class));
        //创建评价对象也会调用此方法，需要区分开
        if (StringUtils.isNotBlank(dto.getName()) && !dto.getIsCreate()) {
            //找出需要变动的缓存
            List<CommentBusinessModelConnectDO> commentBusinessModelConnects = commentBusinessModelConnectDAO.selectByCondition(new Long[]{id});
            List<Long> businessIds = commentBusinessModelConnects.stream().map(CommentBusinessModelConnectDO::getBusinessId).collect(Collectors.toList());
            RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, businessIds);
        }
        return flag;
    }

    @Override
    public Boolean createCommentModel(CommentModelDTO dto) {
        CommentModelDO commentModelDO = dto.clone(CommentModelDO.class);
        Boolean flag = commentModelDAO.createCommentModel(commentModelDO);
        dto.setId(commentModelDO.getId());
        return flag;
    }

    @Override
    public Boolean deleteCommentModels(Long... ids) {
        return commentModelDAO.deleteCommentModels(ids);
    }

    @Override
    public Boolean getCountCondition(CommentModelDTO dto) {
        return commentModelDAO.getCountCondition(dto.clone(CommentModelDO.class));

    }

    @Override
    public List<CommentModelDTO> listCommentModelByIds(Long... ids) {
        List<CommentModelDO> commentModels = commentModelDAO.listCommentModelByIds(ids);
        return ObjectCloneUtils.convertList(commentModels, CommentModelDTO.class, CloneDirection.OPPOSITE);
    }
}