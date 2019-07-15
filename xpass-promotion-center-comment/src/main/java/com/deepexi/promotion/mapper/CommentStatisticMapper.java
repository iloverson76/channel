package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentStatisticDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liaoxiaoxin
 */
@Mapper
public interface CommentStatisticMapper extends BaseMapper<CommentStatisticDO> {

    /**
     * 批量插入统计或者将值更新
     * 如果id或者唯一索引存在冲突，则统计值加1
     *
     * @param list 要插入的集合
     * @return 影响条数
     */
    int batchInsertOrUpdate(@Param("list") List<CommentStatisticDO> list);

    /**
     * 根据code更新countValue
     *
     * @param statisticDoList 评论统计列表
     * @return 影响行数
     */
    int batchUpdateByCode(@Param("statisticDoList") List<CommentStatisticDO> statisticDoList);
}