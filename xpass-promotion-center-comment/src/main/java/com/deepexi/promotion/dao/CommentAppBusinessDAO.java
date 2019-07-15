package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentAppBusinessDO;
import com.deepexi.promotion.domain.CommentAppBusinessQuery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentAppBusinessDAO extends IService<CommentAppBusinessDO> {

    /**
     * 查询
     * @param query
     * @return
     */
    List<CommentAppBusinessDO> listAll(CommentAppBusinessQuery query);

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
