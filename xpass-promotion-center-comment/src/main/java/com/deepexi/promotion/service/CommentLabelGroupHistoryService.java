package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.CommentLabelGroupHistoryDTO;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryQuery;
import java.util.List;

/**
 * comment_label_group_history
 * @author
 */
public interface CommentLabelGroupHistoryService {

    /**
     * 分页获取列表
     *
     * @param dto
     * @param page
     * @param size
     * @return
     */
    List<CommentLabelGroupHistoryDTO> listPageCommentLabelGroupHistorys(CommentLabelGroupHistoryQuery dto);
    /**
     * 获取列表
     *
     * @return
     */
    List<CommentLabelGroupHistoryDTO> listCommentLabelGroupHistorys(CommentLabelGroupHistoryQuery dto);

    /**
     * 获取详情
     *
     * @return
     */
    CommentLabelGroupHistoryDTO getCommentLabelGroupHistory(Long id);

    /**
     * 更新
     *
     * @param id
     * @param dto
     * @return
     */
    Boolean updateCommentLabelGroupHistory(Long id, CommentLabelGroupHistoryDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Boolean insertCommentLabelGroupHistory(CommentLabelGroupHistoryDTO dto);


    /**
     * 批量删除
     *
     * @return
     */
    Boolean deleteCommentLabelGroupHistorys(Long... ids);




}