package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorBusinessService;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDTO;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDTO;
import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.channel.service.DistributorAreaRelationService;
import com.deepexi.channel.service.DistributorBankAccountRelationService;
import com.deepexi.channel.service.DistributorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DistributorBusinessServiceImpl implements DistributorBusinessService {

    @Autowired
    private DistributorService distributorService;

    @Autowired
    DistributorAreaRelationService distributorAreaRelationService;

    @Autowired
    private DistributorBankAccountRelationService distributorBankAccountRelationService;

    @Override
    public long create(DistributorDTO distributor) {

        //经销商信息保存
        long distId=distributorService.create(distributor);

        //区域信息保存
        long areaId=distributor.getAreaId();

        DistributorAreaRelationDTO adto=new DistributorAreaRelationDTO();

        adto.setAreaId(areaId);

        adto.setDistributorId(distId);

        adto.setCreatedBy(distributor.getCreatedBy());

        adto.setCreatedTime(distributor.getCreatedTime());

        adto.setUpdatedBy(distributor.getUpdatedBy());

        adto.setUpdatedTime(distributor.getUpdatedTime());

        distributorAreaRelationService.create(adto);

        //账号信息批量保存
        List<Long> accountIds=distributor.getBankAccountIds();

        List<DistributorBankAccountRelationDTO> barList=new ArrayList<>();

        accountIds.forEach(a->{

            DistributorBankAccountRelationDTO bar=new DistributorBankAccountRelationDTO();

            bar.setBankAccountId(a);

            bar.setDistributorId(distId);

            bar.setCreatedBy(distributor.getCreatedBy());

            bar.setCreatedTime(distributor.getCreatedTime());

            bar.setUpdatedBy(distributor.getUpdatedBy());

            bar.setUpdatedTime(distributor.getUpdatedTime());

            barList.add(bar);
        });

        distributorBankAccountRelationService.create(barList);

        return distId;
    }


}