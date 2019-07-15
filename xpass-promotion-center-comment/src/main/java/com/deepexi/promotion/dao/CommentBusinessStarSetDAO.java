package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentBusinessStarSetDO;
import com.deepexi.promotion.domain.CommentBusinessStarSetDeleteVO;

/**
 * @author zhoujiawen
 */
public interface CommentBusinessStarSetDAO extends IService<CommentBusinessStarSetDO> {

    /**
     * 根据业务对象关联删除星评设置
     * @param businessModelConnectId
     * @return
     */
    Boolean removeByBusinessModelConnectId(Long businessModelConnectId);

    /**
     * 条件删除
     * @param vo 条件
     * @return 删除数量
     */
    int deleteByCondition(CommentBusinessStarSetDeleteVO vo);
}