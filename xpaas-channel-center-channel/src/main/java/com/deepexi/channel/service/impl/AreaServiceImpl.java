package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.AreaDAO;
import com.deepexi.channel.domain.area.*;
import com.deepexi.channel.service.AreaService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


/* 修改的时候再处理上级和路径


        //设置处理(id路径)

        long parentId=newNode.getParentId();

        if (0==parentId) {

            newNode.setPath("/"+String.valueOf(newId));//首次创建

        } else {

            String parent_path=areaDAO.getById(parentId).getPath()+"/"+newId;

            newNode.setPath(parent_path);
        }

        areaDAO.updateById(newNode);
     */

        return newId;
    }

    @Override
    public boolean update(AreaDTO dto) {
        return false;
    }

    @Override
    public List<AreaDTO> findPage(AreaQuery query) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<AreaDO> areaList = areaDAO.listAreaPage(query);

        if(CollectionUtils.isEmpty(areaList)){
            return null;
        }

        return ObjectCloneUtils.convertList(areaList, AreaDTO.class, CloneDirection.OPPOSITE);
    }
}