package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.distributor.DistributorGradeDO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.eo.CcDistributorGrade;

import java.util.List;

public interface DistributorGradeDAO extends  IService<DistributorGradeDO> {


    List<DistributorGradeDO> findPage(DistributorGradeQuery query);
}
