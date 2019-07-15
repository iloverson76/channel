package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryDO;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryQuery;

import java.util.List;

/**
 * comment_label_group_history
 * @author zhangyanping
 */
public interface CommentLabelGroupHistoryDAO extends IService<CommentLabelGroupHistoryDO> {

    /**
     * 分页获取列表
     *
     * @param dto
     * @param page
     * @param size
     * @return
     */
    List<CommentLabelGroupHistoryDO> listPageCommentLabelGroupHistorys(CommentLabelGroupHistoryQuery dto);
    /**
     * 获取列表
     *
     * @return
     */
    List<CommentLabelGroupHistoryDO> listCommentLabelGroupHistorys(CommentLabelGroupHistoryQuery dto);

    /**
     * 获取详情
     *
     * @return
     */
    CommentLabelGroupHistoryDO getCommentLabelGroupHistory(Long id);

    /**
     * 更新
     *
     * @param id
     * @param dto
     * @return
     */
    Boolean updateCommentLabelGroupHistory(CommentLabelGroupHistoryDO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Boolean insertCommentLabelGroupHistory(CommentLabelGroupHistoryDO dto);


    /**
     * 批量删除
     *
     * @return
     */
    Boolean deleteCommentLabelGroupHistorys(Long... ids);

    /**
     * 根据组ID批量删除
     * @param ids
     * @return Boolean
     */
    Boolean deleteBatchByGroupsIds(Long... ids);
}