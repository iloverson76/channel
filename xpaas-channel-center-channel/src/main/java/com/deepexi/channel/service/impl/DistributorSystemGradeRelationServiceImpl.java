package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorSystemGradeRelationDAO;
import com.deepexi.channel.domain.distributor.DistributorSystemGradeRelationDO;
import com.deepexi.channel.domain.distributor.DistributorSystemGradeRelationDTO;
import com.deepexi.channel.service.DistributorSystemGradeRelationService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * created by chenpeng on
 */
public class DistributorSystemGradeRelationServiceImpl implements DistributorSystemGradeRelationService {

    @Autowired
    DistributorSystemGradeRelationDAO distributorSystemGradeRelationDAO;

    @Transactional
    @Override
    public long create(DistributorSystemGradeRelationDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorSystemGradeRelationDO eo=dto.clone(DistributorSystemGradeRelationDO.class, CloneDirection.FORWARD);

        eo.setCreatedTime(new Date());

        eo.setUpdatedTime(new Date());

        distributorSystemGradeRelationDAO.save(eo);

        long id=eo.getId();

        if(id>0){
            return id;
        }

        return 0L;
    }

    @Override
    public Boolean update(DistributorSystemGradeRelationDTO dto) {

        if(null==dto){
            return false;
        }

        DistributorSystemGradeRelationDO eo=dto.clone(DistributorSystemGradeRelationDO.class, CloneDirection.FORWARD);

        if(null==eo){
            return null;
        }

        return distributorSystemGradeRelationDAO.updateById(eo);
    }

    @Transactional
    @Override
    public Boolean delete(List<Long> idList) {

        if(CollectionUtils.isEmpty(idList)){
            return false;
        }

        return distributorSystemGradeRelationDAO.removeByIds(idList);
    }

    @Override
    public Boolean deleteByGradeIds(List<Long> gradeIdList) {

        return distributorSystemGradeRelationDAO.deleteByGradeIds(gradeIdList);
    }

    @Override
    public Boolean deleteBySystemIds(List<Long> systemIdList) {

        if(CollectionUtils.isEmpty(systemIdList)){
            return false;
        }

        return distributorSystemGradeRelationDAO.deleteBySystemIds(systemIdList);
    }

    @Override
    public Boolean deleteByGradeIdAndSystemId(Long gradeId, Long systemId) {

        if(0==gradeId||0==systemId){
            return false;
        }

        Map<String, Object> columnMap = new HashMap<>();

        columnMap.put("distributor_grade_id", gradeId);

        columnMap.put("distributor_system_id",systemId );

        return distributorSystemGradeRelationDAO.removeByMap(columnMap);
    }

    @Override
    public DistributorSystemGradeRelationDTO detail(Long pk) {

        if(0==pk){
            return null;
        }

        DistributorSystemGradeRelationDTO dto= distributorSystemGradeRelationDAO.getById(pk).clone(
                DistributorSystemGradeRelationDTO.class,CloneDirection.OPPOSITE
        );

        if(null!=dto){
            return dto;
        }
        return null;
    }

    @Override
    public DistributorSystemGradeRelationDTO detailByGradeIdAndSystemId(Long gradeId, Long systemId) {

        DistributorSystemGradeRelationDO eo=distributorSystemGradeRelationDAO.detailByGradeIdAndSystemId(gradeId,systemId);

        if(null==eo){
            return null;
        }

        return eo.clone(DistributorSystemGradeRelationDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public List<DistributorSystemGradeRelationDTO> listPageByIds(List<Long> PKOrGradeIdOrSystemIdList) {

        if(CollectionUtils.isEmpty(PKOrGradeIdOrSystemIdList)){
            return null;
        }

        List<DistributorSystemGradeRelationDO> eoList= distributorSystemGradeRelationDAO.listPageByIds(PKOrGradeIdOrSystemIdList);

        if(CollectionUtils.isEmpty(eoList)){
            return null;
        }

        return ObjectCloneUtils.convertList(eoList,DistributorSystemGradeRelationDTO.class,CloneDirection.OPPOSITE);

    }
}
