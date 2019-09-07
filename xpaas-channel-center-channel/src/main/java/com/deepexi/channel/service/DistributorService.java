package com.deepexi.channel.service;

import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.channel.domain.distributor.DistributorQuery;
import com.deepexi.channel.domain.eo.CcDistributor;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_distributor
 */
public interface DistributorService {


    long create(DistributorDTO dto);

    boolean deleteBatch(List<Long> idList);

    List<DistributorDTO> findPage(DistributorQuery query);

    boolean update(DistributorDTO dto);
}