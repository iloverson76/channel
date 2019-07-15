package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.CommentBusinessModelConnectDAO;
import com.deepexi.promotion.dao.CommentBusinessTextSetDAO;
import com.deepexi.promotion.domain.CommentBusinessTextSetDO;
import com.deepexi.promotion.enums.CommentTemplateTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deepexi.promotion.domain.CommentBusinessModelConnectDO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectQuery;
import com.deepexi.promotion.domain.CommentBusinessModelConnectDTO;
import com.deepexi.promotion.service.CommentBusinessModelConnectService;

import java.util.List;

import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author zhangyanping
 */
@Service
@Slf4j
public class CommentBusinessModelConnectServiceImpl implements CommentBusinessModelConnectService {


    @Autowired
    private CommentBusinessModelConnectDAO commentBusinessModelConnectDAO;

    @Autowired
    private CommentBusinessTextSetDAO commentBusinessTextSetDAO;

    @Override
    public List<CommentBusinessModelConnectDTO> listCommentBusinessModelConnects(CommentBusinessModelConnectQuery query) {
        List<CommentBusinessModelConnectDO> list = commentBusinessModelConnectDAO.listCommentBusinessModelConnects(query);
        return ObjectCloneUtils.convertList(list, CommentBusinessModelConnectDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateCommentBusinessModelConnect(Long id, CommentBusinessModelConnectDTO dto) {
        dto.setId(id);
        Boolean result = commentBusinessModelConnectDAO.updateCommentBusinessModelConnect(dto.clone(CommentBusinessModelConnectDO.class));
        RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, dto.getBusinessId());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertCommentBusinessModelConnect(CommentBusinessModelConnectDTO dto) {
        //保存前校验 一个业务的对象不能重复关联
        QueryWrapper<CommentBusinessModelConnectDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("business_id", dto.getBusinessId());
        queryWrapper.eq("model_id", dto.getModelId());
        queryWrapper.eq("app_id", dto.getAppId());
        int n = commentBusinessModelConnectDAO.count(queryWrapper);
        if (n > 0) {
            throw new ApplicationException(ResultEnum.COMMENT_TEMPLATE_EXIST);
        }
        //添加对象关联时向数据库插入一条文本设置
        CommentBusinessModelConnectDO connectDO = dto.clone(CommentBusinessModelConnectDO.class);
        Boolean flag = commentBusinessModelConnectDAO.insertCommentBusinessModelConnect(connectDO);
        CommentBusinessTextSetDO commentBusinessTextSetDO = new CommentBusinessTextSetDO();
        commentBusinessTextSetDO.setBusinessModelConnectId(connectDO.getId());
        commentBusinessTextSetDO.setSupportSettings(CommentConstants.DEFAULT_SUPPORT_SETTING);
        commentBusinessTextSetDO.setType(CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode());
        Boolean textFlag = commentBusinessTextSetDAO.createCommentBusinessTextSet(commentBusinessTextSetDO);
        log.info("对象关联保存成功？：{},生成默认文本设置成功？：{}", flag, textFlag);

        //淘汰当前业务id对应的缓存
        RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, dto.getBusinessId());
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCommentBusinessModelConnects(Long... ids) {
        boolean flag = commentBusinessModelConnectDAO.deleteCommentBusinessModelConnects(ids);

        //淘汰当前业务id对应的缓存
        RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, ids);
        return flag;
    }

    @Override
    public Boolean deleteByCondition(Long[] modelId) {
        return commentBusinessModelConnectDAO.deleteByCondition(modelId);
    }

    @Override
    public List<CommentBusinessModelConnectDO> selectByCondition(Long[] modelIds) {
        return commentBusinessModelConnectDAO.selectByCondition(modelIds);
    }

    @Override
    public List<CommentBusinessModelConnectDTO> listCommentBusinessModelConnects(
            List<CommentBusinessModelConnectQuery> dtoList) {
        List<CommentBusinessModelConnectDO> connectDOS =
                commentBusinessModelConnectDAO.listCommentBusinessModelConnects(dtoList);
        return ObjectCloneUtils.convertList(connectDOS, CommentBusinessModelConnectDTO.class);
    }

}