package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentBusinessStarSetDAO;
import com.deepexi.promotion.domain.CommentBusinessStarSetDO;
import com.deepexi.promotion.domain.CommentBusinessStarSetDeleteVO;
import com.deepexi.promotion.mapper.CommentBusinessStarSetMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * comment_business_label_group_set
 *
 * @author admin
 */
@Repository
public class CommentBusinessStarSetDAOImpl
        extends ServiceImpl<CommentBusinessStarSetMapper, CommentBusinessStarSetDO>
        implements CommentBusinessStarSetDAO {

    @Resource
    private CommentBusinessStarSetMapper commentBusinessStarSetMapper;


    @Override
    public int deleteByCondition(CommentBusinessStarSetDeleteVO vo) {
        return commentBusinessStarSetMapper.deleteByCondition(vo);
    }
    @Override
    public Boolean removeByBusinessModelConnectId(Long businessModelConnectId) {
        CommentBusinessStarSetDO starSetDO = new CommentBusinessStarSetDO();
        starSetDO.setBusinessModelConnectId(businessModelConnectId);
        UpdateWrapper<CommentBusinessStarSetDO> wrapper = new UpdateWrapper<>(starSetDO);
        return this.remove(wrapper);
    }
}

