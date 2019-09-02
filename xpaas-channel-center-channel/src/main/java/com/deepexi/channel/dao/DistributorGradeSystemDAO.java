package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.distributor.DistributorGradeDO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemDO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemQuery;
import com.deepexi.channel.domain.eo.CcDistributorGradeSystem;

import java.util.List;

public interface DistributorGradeSystemDAO extends  IService<DistributorGradeSystemDO> {

    List<DistributorGradeSystemDO> findPage(DistributorGradeSystemQuery query);

}
