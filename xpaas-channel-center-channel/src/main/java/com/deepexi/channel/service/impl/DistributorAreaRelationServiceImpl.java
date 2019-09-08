package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorAreaRelationDAO;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDO;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDTO;
import com.deepexi.channel.service.DistributorAreaRelationService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistributorAreaRelationServiceImpl implements DistributorAreaRelationService {

    @Autowired
    DistributorAreaRelationDAO distributorAreaRelationDAO;

    @Transactional
    @Override
    public Long create(DistributorAreaRelationDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorAreaRelationDO ado=dto.clone(DistributorAreaRelationDO.class, CloneDirection.FORWARD);

        distributorAreaRelationDAO.save(ado);

        return ado.getId();
    }

    @Override
    public boolean createBatch(List<DistributorAreaRelationDTO> dtoList) {

        return distributorAreaRelationDAO.saveBatch(ObjectCloneUtils.
                convertList(dtoList,DistributorAreaRelationDO.class,CloneDirection.FORWARD));
    }

    @Override
    public int deleteBatchByDistributorIds(List<Long> distributorIdList) {

        return distributorAreaRelationDAO.deleteBatchByDistributorIds(distributorIdList);
    }

    @Override
    public DistributorAreaRelationDTO findOneByDistributorId(Long distributorId) {

        DistributorAreaRelationDO eo=distributorAreaRelationDAO.getOne(distributorId);

        if(eo==null){
            return new DistributorAreaRelationDTO();

        }

        return eo.clone(DistributorAreaRelationDTO.class,CloneDirection.OPPOSITE);
    }
}