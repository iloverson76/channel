package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentStatisticDO;
import com.deepexi.promotion.domain.CommentStatisticQuery;

import java.util.List;

/**
 * comment_statistic
 *
 * @author liaoxx
 */
public interface CommentStatisticDAO extends IService<CommentStatisticDO> {

    /**
     * 批量插入统计或者将值更新
     * 如果id或者唯一索引存在冲突，则统计值加1
     *
     * @param list 要插入的集合
     * @return 影响条数
     */
    boolean batchInsertOrUpdate(List<CommentStatisticDO> list);

    /**
     * 根据条件查询评论统一列表
     *
     * @param statisticQuery 查询条件
     * @return 评论统计列表
     */
    List<CommentStatisticDO> listStatistics(CommentStatisticQuery statisticQuery);

    /**
     * 删除评论时，减去countValue
     *
     * @param statisticDoList 评论统计列表
     * @return 是否成功
     */
    boolean reduceCountValueByCode(List<CommentStatisticDO> statisticDoList);
}