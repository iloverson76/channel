package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.AreaTypeDAO;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.mapper.AreaTypeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class AreaTypeDAOImpl extends ServiceImpl<AreaTypeMapper, AreaTypeDO> implements AreaTypeDAO {

    @Override
    public int removeAreaTypeByIds(Set<Long> ids) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id",ids);
        return baseMapper.delete(queryWrapper);
    }

    @Override
    public List<AreaTypeDO> listAreaType(AreaTypeQuery query) {
        return baseMapper.listAreaType(query);
    }
}
