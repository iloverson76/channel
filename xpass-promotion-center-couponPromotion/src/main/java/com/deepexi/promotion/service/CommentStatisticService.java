package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.*;

import java.util.List;

/**
 * comment_statistic
 * @author liaoxx
 */
public interface CommentStatisticService extends IService<CommentStatisticDO> {

    /**
     * 批量插入统计或者将值更新
     * 如果id或者唯一索引存在冲突，则统计值加1
     *
     * @param list 要插入的集合
     * @return 影响条数
     */
    boolean batchInsertOrUpdate(List<CommentStatisticDO> list);

    /**
     * 查询评价目标下的所有统计分类信息
     *
     * @param statisticQuery CommentStatisticQuery
     * @return 评价目标下的所有分类
     */
    List<CommentStatisticDTO> listStatistics(CommentStatisticQuery statisticQuery);

    /**
     * 查询具体统计分类下的所有评论内容(eg: 查询有图的评论)
     *
     * @param statisticTypeQuery 特定分类下评论内容的查询参数
     * @return 评论内容列表
     */
    List<CommentInfoQueryDTO> listCommentByStatisticType(CommentStatisticTypeQuery statisticTypeQuery);
}