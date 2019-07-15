package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentDetailDO;
import com.deepexi.promotion.domain.CommentDetailDTO;
import com.deepexi.promotion.domain.CommentDetailReplyInputDTO;

/**
 * comment_detail
 *
 * @author admin
 */
public interface CommentDetailService extends IService<CommentDetailDO> {

    /**
     * 回复评价
     *
     * @param input 参数
     * @return 插入成功后的值
     */
    CommentDetailDTO createReply(CommentDetailReplyInputDTO input);

    /**
     * 追加评论
     *
     * @param input 参数
     * @return 成功与否
     */
    CommentDetailDTO addToComment(CommentDetailReplyInputDTO input);

    /**
     * 查找评价明细
     *
     * @param detailId 参数
     * @return 评价明细
     */
    CommentDetailDTO getCommentDetailDTOById(Long detailId);

}