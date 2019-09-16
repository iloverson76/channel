package com.deepexi.channel.businness;

import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemQuery;

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

    List<DistributorGradeSystemDTO> getDistributorGradeSystemByDistributorId(long distributorId);
}