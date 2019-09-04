package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorBusinessService;
import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.channel.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DistributorBusinessServiceImpl implements DistributorBusinessService {

    @Autowired
    private DistributorService distributorService;

    @Autowired
    private DistributorGradeRelationService distributorGradeRelationService;

    @Autowired
    private DistributorBankAccountRelationService distributorBankAccountRelationService;


    @Override
    public int create(DistributorDTO dto) {



       // distributorGradeRelationService.create()

        return 0;
    }
}