package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.*;

import java.util.List;

/**
 * promotion
 *
 * @author admin
 */
public interface CommentInfoService extends IService<CommentInfoDO> {

    /**
     * 分页查询评论内容列表
     *
     * @param query 参数
     * @return {@see List<CommentInfoQueryDTO>}
     */
    List<CommentInfoQueryDTO> listCommentInfoQueryDTO(CommentInfoQuery query);

    /**
     * 查询评论详情(tree)
     * @param query 参数
     * @return {@see CommentDetailResultDTO}
     */
    CommentDetailResultDTO getCommentDetailWithTree(CommentInfoQuery query);

    /**
     * 发表评价
     *
     * @param input 参数
     * @return 成功与否
     */
    Boolean createComment(CommentInfoInputDTO input);

    /**
     * 通过评明细id查询星评评价
     *
     * @param commentDetailId 评价明细id
     * @return List<CommentStarDTO>
     */
    List<CommentStarDTO> listCommentStarDTO(Long commentDetailId);

    /**
     * 删除评价
     *
     * @param commentDetailId 评价明细id
     * @return 成功与否
     */
    Boolean deleteComment(Long commentDetailId);

    /**
     * 查询评论数量
     *
     * @param commentCountQuery {@link CommentCountQuery}
     * @return 评论数量
     */
    Long commentCount(CommentCountQuery commentCountQuery);

    /**
     * 根据统计分类查询评价列表列表
     *
     * @param statisticTypeQuery 条件
     * @return List<CommentInfoQueryDTO>
     */
    List<CommentInfoQueryDTO> listCommentByStatisticType(CommentStatisticTypeQuery statisticTypeQuery);

}