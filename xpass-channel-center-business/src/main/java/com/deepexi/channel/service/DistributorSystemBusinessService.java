package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;
import com.deepexi.channel.domain.StoreDistributorDTO;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorSystemBusinessService {

    /**
     * 分页获取列表
     */
    List<DistributorGradeSystemDTO> findPage(DistributorGradeSystemQuery query);

    /**
     * 获取详情
     */
    DistributorGradeSystemDTO detail(Long pk);

    List<StoreDistributorDTO> getDistributorGradeSystemByDistributorId(long distributorId);

    boolean delete(List<Long> idList);
}