package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorBankAccountRelationDAO;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDO;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDTO;
import com.deepexi.channel.service.DistributorBankAccountRelationService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributorBankAccountRelationServiceImpl implements DistributorBankAccountRelationService {

    @Autowired
    DistributorBankAccountRelationDAO distributorBankAccountRelationDAO;

    @Override
    public boolean create(List<DistributorBankAccountRelationDTO> dtoList) {

        if(CollectionUtils.isEmpty(dtoList)){
            return false;
        }

        List<DistributorBankAccountRelationDO> eoList=
        ObjectCloneUtils.convertList(dtoList,DistributorBankAccountRelationDO.class,CloneDirection.FORWARD);

        distributorBankAccountRelationDAO.saveBatch(eoList);

        return Boolean.TRUE;
    }

    @Override
    public boolean delete(List<Long> idList) {
        return false;
    }


}