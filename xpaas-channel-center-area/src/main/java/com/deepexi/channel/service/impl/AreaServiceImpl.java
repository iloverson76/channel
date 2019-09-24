package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.AreaDAO;
import com.deepexi.channel.domain.AreaDO;
import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaQuery;
import com.deepexi.channel.service.AreaService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService{

    @Autowired
    AreaDAO areaDAO;

    @Transactional
    @Override
    public Long create(AreaDTO dto) {

        if(null==dto){
            return 0L;
        }
        dto.setParentId(0L);
        AreaDO newNode=dto.clone(AreaDO.class,CloneDirection.FORWARD);

        areaDAO.save(newNode);

        Long newId=newNode.getId();

        areaDAO.updateById(newNode);

        return newId;
    }

    @Override
    public AreaDTO getAreaById(Long pk) {

        if(pk<=0){
            return  new AreaDTO();
        }

        AreaDO eo=areaDAO.getById(pk);

        if(null==eo){
            return new AreaDTO();
        }

        return eo.clone(AreaDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public boolean update(AreaDTO dto) {

        if(null==dto){
            return false;
        }

        areaDAO.updateById(dto.clone(AreaDO.class,CloneDirection.FORWARD));

        return true;
    }

    @Override
    public boolean deleteById(Long id) {

        if(id<=0){
            return false;
        }

        areaDAO.removeById(id);

        return true;
    }

    @Override
    public List<AreaDTO> listChildrenAreas(Long areaId) {

        List<AreaDO> eoList=areaDAO.listChildrenAreas(areaId);

        if(CollectionUtils.isEmpty(eoList)){

            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList,AreaDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public List<AreaDTO> listLinkedAreasByType(Long areaTypeId) {

        List<AreaDO> eoList= areaDAO.listLinkedAreasByType(areaTypeId);

         if(CollectionUtils.isEmpty(eoList)){
             return Collections.emptyList();
        }

         return ObjectCloneUtils.convertList(eoList,AreaDTO.class,CloneDirection.FORWARD);
    }

    @Override
    public boolean updateBatch(List<AreaDTO> dtoList) {

        if(CollectionUtils.isEmpty(dtoList)){
            return false;
        }

        areaDAO.updateBatchById(ObjectCloneUtils.convertList(dtoList,AreaDO.class,CloneDirection.FORWARD));

        return Boolean.TRUE;
    }

    @Override
    public List<AreaDTO> findAllByIds(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return Collections.emptyList();
        }

        List<AreaDO> eoList= areaDAO.findAllByIds(ids);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList,AreaDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return false;
        }

        return areaDAO.removeByIds(ids);
    }

    @Override
    public List<AreaDTO> findPage(AreaQuery query) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<AreaDO> areaList = areaDAO.listAreaPage(query);

        if(CollectionUtils.isEmpty(areaList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(areaList, AreaDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public List<AreaDTO> findTree() {

        List<AreaDO> areaList = areaDAO.findTree();

        if(CollectionUtils.isEmpty(areaList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(areaList, AreaDTO.class, CloneDirection.OPPOSITE);

    }


}