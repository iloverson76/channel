package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentSystemAppDO;
import com.deepexi.promotion.domain.CommentSystemAppQuery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentSystemAppDAO extends IService<CommentSystemAppDO> {

    /**
     * 查找所有
     * @param query
     * @return
     */
    List<CommentSystemAppDO> listAllCommentSystemApp(CommentSystemAppQuery query);

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
    int updateBatch(List<CommentSystemAppDO> list);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<CommentSystemAppDO> list);

    /**
     * 插入或更新
     * @param record
     * @return
     */
    int insertOrUpdate(CommentSystemAppDO record);

    /**
     * 根据参数插入或更新
     * @param record
     * @return
     */
    int insertOrUpdateSelective(CommentSystemAppDO record);
}
