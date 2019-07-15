package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentDetailCheckDO;
import java.util.List;


/**
 * comment_detail_check
 */
public interface CommentDetailCheckDAO extends IService<CommentDetailCheckDO> {
    /**
     * 批量插入
     * @param detailCheckDOList
     * @return 是否成功
     */
    boolean insertBatch(List<CommentDetailCheckDO> detailCheckDOList);
}