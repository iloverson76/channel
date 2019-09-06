package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.distributor.DistributorDO;
import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.channel.domain.distributor.DistributorQuery;

import java.util.List;

public interface DistributorDAO extends  IService<DistributorDO> {

    List<DistributorDO> findPage(DistributorQuery query);
}
