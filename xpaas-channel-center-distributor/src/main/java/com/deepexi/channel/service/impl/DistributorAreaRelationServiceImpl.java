package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorAreaRelationDAO;
import com.deepexi.channel.domain.DistributorAreaRelationDO;
import com.deepexi.channel.domain.DistributorAreaRelationDTO;
import com.deepexi.channel.service.DistributorAreaRelationService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public int deleteBatchByAreaIds(List<Long> areaIdList) {

        if(CollectionUtils.isEmpty(areaIdList)){
            return 0;
        }
        return distributorAreaRelationDAO.deleteBatchByAreaIds(areaIdList);
    }

    @Override
    public List<DistributorAreaRelationDTO> findAllByDistributorId(Long distributorId) {

        if(distributorId<=0){
            return Collections.emptyList();
        }

        List<DistributorAreaRelationDO> eoList=distributorAreaRelationDAO.findAllByDistributorId(distributorId);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();

        }

        return ObjectCloneUtils.convertList(eoList,DistributorAreaRelationDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public List<DistributorAreaRelationDTO> findAllByAreaIds(List<Long> areaIdList) {

        if(CollectionUtils.isEmpty(areaIdList)){
            return Collections.emptyList();
        }

        List<DistributorAreaRelationDO> eoList=distributorAreaRelationDAO.findAllByAreaIds(areaIdList);

        return ObjectCloneUtils.convertList(eoList,DistributorAreaRelationDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean deleteByDistributorId(Long distributorId) {

        if(distributorId<=0){
            return false;
        }

        distributorAreaRelationDAO.deleteBatchByDistributorIds(Collections.singletonList(distributorId));

        return Boolean.TRUE;
    }
}