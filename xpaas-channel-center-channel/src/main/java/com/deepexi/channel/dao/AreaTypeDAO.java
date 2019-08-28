package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeQuery;

import java.util.List;
import java.util.Set;

public interface AreaTypeDAO extends  IService<AreaTypeDO> {

    int removeAreaTypeByIds(Set<Long> ids);

    List<AreaTypeDO> listAreaType(AreaTypeQuery query);
}
