package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.CommentBusinessModelConnectDO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectDTO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectQuery;
import java.util.List;

/**
 * comment_business_model_connect
 * @author zhangyanping
 */
public interface CommentBusinessModelConnectService {


    /**
     * 获取列表
     * @param dto 查询参数
     * @return List<CommentBusinessModelConnectDTO>
     */
    List<CommentBusinessModelConnectDTO> listCommentBusinessModelConnects(CommentBusinessModelConnectQuery dto);

    /**
     * 获取列表
     * @param dtoList 查询参数
     * @return List<CommentBusinessModelConnectDTO>
     */
    List<CommentBusinessModelConnectDTO> listCommentBusinessModelConnects(List<CommentBusinessModelConnectQuery> dtoList);
    /**
     * 更新
     *
     * @param id 主键
     * @param dto 修改实体
     * @return Boolean
     */
    Boolean updateCommentBusinessModelConnect(Long id, CommentBusinessModelConnectDTO dto);

    /**
     * 新增
     *
     * @param dto 新增实体
     * @return Boolean
     */
    Boolean insertCommentBusinessModelConnect(CommentBusinessModelConnectDTO dto);


    /**
     * 批量删除
     * @param ids 主键
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