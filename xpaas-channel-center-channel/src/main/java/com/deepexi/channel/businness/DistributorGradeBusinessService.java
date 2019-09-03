package com.deepexi.channel.businness;

import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeBusiDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorGradeBusinessService {

    /**
      获取详情
    */
    DistributorGradeDTO detail(Long gradeId,Long systemId);

    /**
     * 分页获取列表
     */
    List<DistributorGradeBusiDTO> findPage(DistributorGradeQuery query);

}