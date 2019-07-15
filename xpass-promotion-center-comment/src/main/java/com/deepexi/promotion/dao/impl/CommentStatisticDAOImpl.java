package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentStatisticDAO;
import com.deepexi.promotion.domain.CommentStatisticDO;
import com.deepexi.promotion.domain.CommentStatisticQuery;
import com.deepexi.promotion.mapper.CommentStatisticMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Repository
@Slf4j
public class CommentStatisticDAOImpl
        extends ServiceImpl<CommentStatisticMapper, CommentStatisticDO> implements CommentStatisticDAO {

    @Resource
    private CommentStatisticMapper commentStatisticMapper;

    @Override
    public boolean batchInsertOrUpdate(List<CommentStatisticDO> list) {
        return commentStatisticMapper.batchInsertOrUpdate(list) > 0;
    }

    @Override
    public List<CommentStatisticDO> listStatistics(CommentStatisticQuery statisticQuery) {
        LambdaQueryWrapper<CommentStatisticDO> wrapper = new QueryWrapper<CommentStatisticDO>().lambda();
        wrapper.eq(CommentStatisticDO::getAppId, statisticQuery.getAppId())
                .eq(CommentStatisticDO::getAppBusinessId, statisticQuery.getAppBusinessId())
                .eq(CommentStatisticDO::getModelId, statisticQuery.getModelId())
                .eq(CommentStatisticDO::getTargetId, statisticQuery.getTargetId())
                .eq(CommentStatisticDO::getTenantId, statisticQuery.getTenantId());
        return commentStatisticMapper.selectList(wrapper);
    }

    @Override
    public boolean reduceCountValueByCode(List<CommentStatisticDO> statisticDoList) {
        return commentStatisticMapper.batchUpdateByCode(statisticDoList) > 0;
    }
}