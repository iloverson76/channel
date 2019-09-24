package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorGradeDAO;
import com.deepexi.channel.domain.DistributorGradeDO;
import com.deepexi.channel.domain.DistributorGradeDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DistributorGradeServiceImpl implements DistributorGradeService {

    @Autowired
    private DistributorGradeDAO distributorGradeDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(DistributorGradeDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorGradeDO newNode=dto.clone(DistributorGradeDO.class, CloneDirection.FORWARD);

        distributorGradeDAO.save(newNode);

        long newId=newNode.getId();

        return newId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateById(DistributorGradeDTO dto) {

        if(dto==null){
            return null;
        }

        return distributorGradeDAO.updateById(dto.clone(DistributorGradeDO.class,CloneDirection.FORWARD));
    }

    @Override
    public boolean updateBatchById(List<DistributorGradeDTO> dtoList) {

        if(CollectionUtils.isEmpty(dtoList)){
            return false;
        }

        return distributorGradeDAO.updateBatchById(ObjectCloneUtils.convertList(dtoList,DistributorGradeDO.class,
                CloneDirection.FORWARD));
    }

    @Override
    public List<String> listDistributorGradeCode(Long systemId) {

        List<String> codeList=distributorGradeDAO.listDistributorGradeCode(systemId);

        if(CollectionUtils.isEmpty(codeList)){
            return Collections.emptyList();
        }
        return codeList;
    }

    @Override
    public List<String> listDistributorGradeName(Long systemId) {

        List<String> nameList=distributorGradeDAO.listDistributorGradeName(systemId);

        if(CollectionUtils.isEmpty(nameList)){
            return Collections.emptyList();
        }
        return nameList;
    }

    @Override
    public List<String> listDistributorGradeNameEn(Long systemId) {

        List<String> nameEnList=distributorGradeDAO.listDistributorGradeNameEn(systemId);

        if(com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isEmpty(nameEnList)){
            return Collections.emptyList();
        }
        return nameEnList;
    }

    @Override
    public DistributorGradeDTO getById(Long pk) {

        if(0==pk){
            return null;
        }

        DistributorGradeDO eo=distributorGradeDAO.getById(pk);

        if(null==eo){
            return new DistributorGradeDTO();
        }

        return eo.clone(DistributorGradeDTO.class,CloneDirection.OPPOSITE);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delete(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return false;
        }

        ids.forEach(id->{
            delete(id);
        });

        return Boolean.TRUE;
    }

    public boolean delete(long id) {

        List<DistributorGradeDTO> children=listChildrenNodes(id);

        if(CollectionUtils.isNotEmpty(children)){

            children.forEach(child->{
                child.setParentId(0L);
            });

            List<DistributorGradeDO> childrenDO=ObjectCloneUtils.convertList(children,
                    DistributorGradeDO.class,CloneDirection.FORWARD);

            distributorGradeDAO.updateBatchById(childrenDO);
       }
       return distributorGradeDAO.removeById(id);
    }

    @Override
    public List<DistributorGradeDTO> listChildrenNodes(Long id){

        log.info("查找等级下的子节点");

        if(id<=0){
            return Collections.emptyList();
        }

        List<DistributorGradeDTO> dtoList=findPage(new DistributorGradeQuery());

        if(CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }

        List<DistributorGradeDTO> children=new ArrayList<>();

        //查出所有子节点:新建的时候已经归属了某一个体系
        dtoList.forEach(dto->{

            if(dto.getParentId()==id){

                children.add(dto);
            }
        });
        return children;
    }

    @Override
    public List<DistributorGradeDTO> findPage(DistributorGradeQuery query) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<DistributorGradeDO> eoList = distributorGradeDAO.findPage(query);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList, DistributorGradeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public List<DistributorGradeDTO> findAllBySystem(Long systemId) {

        if(null==systemId||systemId<0||systemId==0){
            return Collections.emptyList();
        }

        List<DistributorGradeDO> eoList=distributorGradeDAO.findAllBySystem(systemId);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList,DistributorGradeDTO.class,CloneDirection.OPPOSITE);
    }
}