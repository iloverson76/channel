package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.deepexi.channel.dao.DistributorDAO;
import com.deepexi.channel.domain.DistributorDO;
import com.deepexi.channel.domain.DistributorDTO;
import com.deepexi.channel.domain.DistributorQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deepexi.channel.service.DistributorService;

import java.util.Collections;
import java.util.List;

import com.github.pagehelper.PageHelper;

@Service
public class DistributorServiceImpl implements DistributorService {

    @Autowired
    DistributorService distributorService;

    @Autowired
    DistributorDAO distributorDAO;

    @Override
    public long create(DistributorDTO dto) {

        if(null==dto){
            return 0L;
        }

//        //只保存直接上级关系
//        if(dto.getLimitedParent()==0){//如果不指定上级,前端传parentId=0过来.这一版无法设置路径
//
//            dto.setParentId(0L);
//        }

        DistributorDO newNode=dto.clone(DistributorDO.class, CloneDirection.FORWARD);

        distributorDAO.save(newNode);

        return newNode.getId();

    }

    @Override
    public boolean deleteBatch(List<Long> idList) {

        distributorDAO.removeByIds(idList);

        return Boolean.TRUE;
    }

    @Override
    public List<DistributorDTO> findPage(DistributorQuery query) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<DistributorDO> eoList = distributorDAO.findPage(query);

        if(CollectionUtil.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList, DistributorDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public boolean update(DistributorDTO dto) {

        if(dto==null){
            return false;
        }
        return distributorDAO.updateById(dto.clone(DistributorDO.class,CloneDirection.FORWARD));
    }

    @Override
    public DistributorDTO getById(Long id) {

        DistributorDO eo=distributorDAO.getById(id);

        if(null==eo){
            return new DistributorDTO();
        }

        return eo.clone(DistributorDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public List<String> listDistributorCode() {

        List<String> codeList=distributorDAO.listDistributorCode();

        if(CollectionUtils.isEmpty(codeList)){
            return Collections.emptyList();
        }
        return codeList;
    }

    @Override
    public List<String> listDistributorName() {

        List<String> nameList=distributorDAO.listDistributorName();

        if(CollectionUtils.isEmpty(nameList)){
            return Collections.emptyList();
        }
        return nameList;
    }

    @Override
    public List<String> listDistributorNameEn() {

        List<String> nameEnList=distributorDAO.listDistributorNameEn();

        if(CollectionUtils.isEmpty(nameEnList)){
            return Collections.emptyList();
        }
        return nameEnList;
    }


}