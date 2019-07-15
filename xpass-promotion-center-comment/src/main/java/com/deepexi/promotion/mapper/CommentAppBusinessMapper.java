package com.deepexi.promotion.mapper;

import java.util.List;

import com.deepexi.promotion.domain.CommentAppBusinessQuery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentAppBusinessDO;
import com.deepexi.promotion.domain.CommentAppBusinessDTO;

@Mapper
public interface  CommentAppBusinessMapper extends BaseMapper<CommentAppBusinessDO> {

    /**
     * 分页查找
     * @param query
     * @return
     */
    Page<CommentAppBusinessDO> findByPage(CommentAppBusinessQuery query);

    /**
     * 查询全部
     * @param query
     * @return
     */
    List<CommentAppBusinessDO> findAll(CommentAppBusinessQuery query);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateBatch(List<CommentAppBusinessDO> list);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<CommentAppBusinessDO> list);

    /**
     * 插入或更新
     * @param record
     * @return
     */
    int insertOrUpdate(CommentAppBusinessDO record);

    /**
     * 按参数插入或更新
     * @param record
     * @return
     */
    int insertOrUpdateSelective(CommentAppBusinessDO record);
}