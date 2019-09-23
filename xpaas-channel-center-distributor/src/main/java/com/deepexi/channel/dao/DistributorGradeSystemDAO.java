package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.DistributorGradeSystemDO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;

import java.util.List;

public interface DistributorGradeSystemDAO extends  IService<DistributorGradeSystemDO> {

    List<DistributorGradeSystemDO> findPage(DistributorGradeSystemQuery query);

}
