package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.DistributorDO;
import com.deepexi.channel.domain.DistributorQuery;

import java.util.List;

public interface DistributorDAO extends  IService<DistributorDO> {

    List<DistributorDO> findPage(DistributorQuery query);
}
