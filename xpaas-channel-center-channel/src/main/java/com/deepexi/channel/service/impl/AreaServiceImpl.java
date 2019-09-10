package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.AreaDAO;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
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
        AreaDO newNode=dto.clone(AreaDO.class,CloneDirection.FORWARD);

        //save后,ado是插入数据库后返回的新数据,包括id,ado克隆要拆成两步写
        areaDAO.save(newNode);

        long newId=newNode.getId();

        //设置处理(id路径)

        long parentId=newNode.getParentId();

        if (0==parentId) {

            newNode.setPath("/"+String.valueOf(newId));//首次创建

        } else {

            String parent_path=areaDAO.getById(parentId).getPath()+"/"+newId;

            newNode.setPath(parent_path);
        }

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
    public boolean deleteBatch(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return false;
        }

        ids.forEach(id->{
            deleteById(id);
        });

        return true;
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

}