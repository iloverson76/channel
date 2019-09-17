package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorGradeDAO;
import com.deepexi.channel.domain.distributor.DistributorGradeDO;
import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DistributorGradeServiceImpl implements DistributorGradeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

        //路径处理(id)--不维护路径,性能成本可以忽略
        long newId=newNode.getId();

 /*       long parentId=newNode.getParentId();

        if (0==parentId) {

            newNode.setPath(String.valueOf(newId));//首次创建

        } else {

            String parent_path=distributorGradeDAO.getById(parentId).getPath()+"/"+newId;

            newNode.setPath(parent_path);
        }

        distributorGradeDAO.updateById(newNode);
*/
        return newId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(DistributorGradeDTO dto) {

        long id=dto.getId();

        DistributorGradeDTO origDTO=getById(id);

        //根节点修改的冲突
        if(dto.getRoot()==1){

            findPage(new DistributorGradeQuery()).forEach(grade->{

                if(grade.getRoot()==1){
                    throw new ApplicationException("已存在一个根节点"+"["+dto.getDistributorGradeName()+"]");
                }
            });
        }

        //改变所属体系
        long newSystemId=dto.getGradeSystemId();

        long origSystemId=origDTO.getGradeSystemId();

        if(origSystemId==newSystemId){

            List<DistributorGradeDTO> children=listChildrenNodes(id);

            children.forEach(child->{
                child.setGradeSystemId(newSystemId);
            });

            List<DistributorGradeDO> childrenDO=ObjectCloneUtils.convertList(children,
                    DistributorGradeDO.class,CloneDirection.FORWARD);

            distributorGradeDAO.updateBatchById(childrenDO);

        }

        return distributorGradeDAO.updateById(dto.clone(DistributorGradeDO.class,CloneDirection.FORWARD));
    }

    @Override
    public DistributorGradeDTO getById(Long pk) {

        if(0==pk){
            return new DistributorGradeDTO();
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

    private List<DistributorGradeDTO> listChildrenNodes(long id){
        //修改所有子节点的parentId=0
        List<DistributorGradeDTO> dtoList=findPage(new DistributorGradeQuery());

        List<DistributorGradeDTO> children=new ArrayList<>();

        dtoList.forEach(dto->{

            if(dto.getParentId()==id){//查出所有子节点:新建的时候已经归属了某一个体系

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
    public boolean validateGradeCode(String garedCode) {

        int count= distributorGradeDAO.getByCode(garedCode);
        if(count>0){
            throw new ApplicationException(ResultEnum.GRADE_NAME_DUPLICATED);
        }
        return Boolean.TRUE;
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