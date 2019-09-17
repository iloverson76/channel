package com.deepexi.channel.businness;

import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.domain.store.StoreDistributorDTO;

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
}