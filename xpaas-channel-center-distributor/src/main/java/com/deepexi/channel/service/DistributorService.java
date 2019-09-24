package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorDTO;
import com.deepexi.channel.domain.DistributorQuery;

import java.util.List;

/**
 * cc_distributor
 */
public interface DistributorService {


    long create(DistributorDTO dto);

    boolean deleteBatch(List<Long> idList);

    List<DistributorDTO> findPage(DistributorQuery query);

    boolean update(DistributorDTO dto);

    DistributorDTO getById(Long id);

    List<String> listDistributorCode();

    List<String> listDistributorName();

    List<String> listDistributorNameEn();
}