package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemDO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemQuery;

import java.util.List;

public interface DistributorGradeSystemDAO extends  IService<DistributorGradeSystemDO> {

    List<DistributorGradeSystemDO> findPage(DistributorGradeSystemQuery query);

}
