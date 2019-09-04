package com.deepexi.channel.businness;

import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeBusiDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorBusinessService {

   int create(DistributorDTO dto);

}