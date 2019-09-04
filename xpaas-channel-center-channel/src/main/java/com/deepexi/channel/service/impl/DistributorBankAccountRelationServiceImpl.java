package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorBankAccountRelationDAO;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDO;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDTO;
import com.deepexi.channel.service.DistributorBankAccountRelationService;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistributorBankAccountRelationServiceImpl implements DistributorBankAccountRelationService {

    @Autowired
    DistributorBankAccountRelationDAO distributorBankAccountRelationDAO;

    @Override
    public long create(DistributorBankAccountRelationDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorBankAccountRelationDO eo=dto.clone(DistributorBankAccountRelationDO.class, CloneDirection.FORWARD);

        distributorBankAccountRelationDAO.save(eo);

        return eo.getId();
    }
}