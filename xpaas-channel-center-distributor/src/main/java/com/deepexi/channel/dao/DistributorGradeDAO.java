package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.DistributorGradeDO;
import com.deepexi.channel.domain.DistributorGradeQuery;

import java.util.List;

public interface DistributorGradeDAO extends  IService<DistributorGradeDO> {


    List<DistributorGradeDO> findPage(DistributorGradeQuery query);

    List<DistributorGradeDO> listParentNodesForCreate(long systemId,String path);

    int getByCode(String garedCode);

    List<DistributorGradeDO> findAllBySystem(Long systemId);

    List<String> listDistributorGradeCode(Long systemId);

    List<String> listDistributorGradeName(Long systemId);

    List<String> listDistributorGradeNameEn(Long systemId);
}
