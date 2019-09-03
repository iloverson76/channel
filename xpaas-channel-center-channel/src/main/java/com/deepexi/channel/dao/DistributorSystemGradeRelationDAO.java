package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.distributor.DistributorSystemGradeRelationDO;
import com.deepexi.channel.domain.distributor.DistributorSystemGradeRelationDTO;

import java.util.List;
import java.util.Set;

public interface DistributorSystemGradeRelationDAO extends IService<DistributorSystemGradeRelationDO> {

    boolean deleteByGradeIds(List<Long> gradeIdList);

    boolean deleteBySystemIds(List<Long> SystemIdList);

    DistributorSystemGradeRelationDO detailByGradeIdAndSystemId(Long gradeId, Long SystemId);

    List<DistributorSystemGradeRelationDO> listPageByIds(List<Long> PKOrGradeIdOrSystemIdList);
}
