package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorGradeRelationDAO;
import com.deepexi.channel.domain.DistributorGradeRelationDO;
import com.deepexi.channel.domain.DistributorGradeRelationDTO;
import com.deepexi.channel.service.DistributorGradeRelationService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DistributorGradeRelationServiceImpl implements DistributorGradeRelationService {

    @Autowired
    DistributorGradeRelationDAO distributorGradeRelationDAO;

    @Override
    public long create(DistributorGradeRelationDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorGradeRelationDO eo=dto.clone(DistributorGradeRelationDO.class, CloneDirection.FORWARD);

        distributorGradeRelationDAO.save(eo);

        return eo.getId();
    }

    @Override
    public boolean createBatch(List<DistributorGradeRelationDTO> dtoList) {

        if(CollectionUtils.isEmpty(dtoList)){
            return false;
        }

        return distributorGradeRelationDAO.saveBatch(ObjectCloneUtils.
                convertList(dtoList,DistributorGradeRelationDO.class,CloneDirection.FORWARD));
    }

    @Override
    public int deleteByDistributorId(long distributorId) {

        if(distributorId<=0){
            return 0;
        }

        return distributorGradeRelationDAO.deleteByDistributorId( distributorId);
    }

    @Override
    public int deleteBatchByDistributorIds(List<Long> distributorIdList) {

        if(CollectionUtils.isEmpty(distributorIdList)){
            return 0;
        }

        return distributorGradeRelationDAO.deleteBatchByDistributorIds(distributorIdList);
    }

    @Override
    public DistributorGradeRelationDTO findOne(long distributorId, long gradeId) {

        if(distributorId<=0||gradeId<=0){
            return null;
        }

        DistributorGradeRelationDO eo=distributorGradeRelationDAO.findOne(distributorId, gradeId);

        if(null==eo){
            return null;
        }

        return eo.clone(DistributorGradeRelationDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public List<DistributorGradeRelationDTO> findAllByDistributorIds(List<Long> distributorIds) {

        if(CollectionUtils.isEmpty(distributorIds)){
            return null;
        }

        List<DistributorGradeRelationDO> eoList=distributorGradeRelationDAO.findAllByDistributorIds(distributorIds);

        if(CollectionUtils.isEmpty(eoList)){
            return null;
        }

        return ObjectCloneUtils.convertList(eoList,DistributorGradeRelationDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public boolean updateBatchById(List<DistributorGradeRelationDTO> dtoList) {

        if(CollectionUtils.isEmpty(dtoList)){
            return false;
        }

        return distributorGradeRelationDAO.updateBatchById(ObjectCloneUtils.convertList(dtoList,
                DistributorGradeRelationDO.class,CloneDirection.FORWARD));
    }

    @Override
    public boolean updateBatchByDistributorId(List<DistributorGradeRelationDTO> dtoList) {

        if(CollectionUtils.isEmpty(dtoList)){
            return false;
        }

        List<DistributorGradeRelationDO> eoList= ObjectCloneUtils.convertList(dtoList,DistributorGradeRelationDO.class,
                CloneDirection.FORWARD);

        return distributorGradeRelationDAO.updateBatchById(eoList);
    }

    @Override
    public List<DistributorGradeRelationDTO> findAllByGradeId(Long gradeId) {

        List<DistributorGradeRelationDO> eoList=distributorGradeRelationDAO.findAllByGradeId(gradeId);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList,DistributorGradeRelationDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public List<DistributorGradeRelationDTO> findAllByGradeIds(List<Long> gradeIds) {

        if(CollectionUtils.isEmpty(gradeIds)){
            return Collections.emptyList();
        }

        List<DistributorGradeRelationDO> eoList=distributorGradeRelationDAO.findAllByGradeIds(gradeIds);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList,DistributorGradeRelationDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public List<DistributorGradeRelationDTO> findAllBySystemIds(List<Long> systemIds) {

        if(CollectionUtils.isEmpty(systemIds)){
            return Collections.emptyList();
        }

        List<DistributorGradeRelationDO> eoList=distributorGradeRelationDAO.findAllBySystemIds(systemIds);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList,DistributorGradeRelationDTO.class,CloneDirection.OPPOSITE);
    }

}