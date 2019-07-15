package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentBusinessModelConnectDO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectQuery;

import java.util.List;

/**
 * comment_business_model_connect
 * @author zhangyanping
 */
public interface CommentBusinessModelConnectDAO extends IService<CommentBusinessModelConnectDO> {


    /**
     * 获取列表
     * @param dto 查询参数
     * @return List<CommentBusinessModelConnectDO>
     */
    List<CommentBusinessModelConnectDO> listCommentBusinessModelConnects(CommentBusinessModelConnectQuery dto);

    /**
     * 获取列表
     * @param entityList 查询参数列表
     * @return List<CommentBusinessModelConnectDO>
     */
    List<CommentBusinessModelConnectDO> listCommentBusinessModelConnects(List<CommentBusinessModelConnectQuery> entityList);

    /**
     * 更新
     *
     * @param dao 更新实体
     * @return Boolean
     */
    Boolean updateCommentBusinessModelConnect(CommentBusinessModelConnectDO dao);

    /**
     * 新增
     *
     * @param dao 新增对象
     * @return Boolean
     */
    Boolean insertCommentBusinessModelConnect(CommentBusinessModelConnectDO dao);


    /**
     * 批量删除
     * @param ids 批量实体
     * @return Boolean
     */
    Boolean deleteCommentBusinessModelConnects(Long... ids);

    /**
     * 条件删除
     * @param modelId 对象ID
     * @return Boolean
     */
    Boolean deleteByCondition(Long[] modelId);

    List<CommentBusinessModelConnectDO> selectByCondition(Long[] modelIds);
}