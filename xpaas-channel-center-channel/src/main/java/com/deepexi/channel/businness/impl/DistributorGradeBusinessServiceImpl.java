package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.service.DistributorGradeRelationService;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DistributorGradeBusinessServiceImpl implements DistributorGradeBusinessService {

    @Autowired
    private DistributorGradeService distributorGradeService;

    @Autowired
    private DistributorGradeSystemService distributorGradeSystemService;

    @Autowired
    private DistributorGradeRelationService distributorGradeRelationService;

    @Autowired
    private DistributorService distributorService;

    @Override
    public Long create(DistributorGradeDTO dto) {

        DistributorGradeQuery query = new DistributorGradeQuery();

        Long systemId=dto.getGradeSystemId();

        query.setSystemId(systemId);

        List<DistributorGradeDTO> pageList = distributorGradeService.findPage(query);

        if(CollectionUtil.isEmpty(pageList)){

            //首次新建必须是根节点,因为不是根节点的话上一级是必填的
            dto.setRoot(1);

        }else{
            pageList.forEach(grade->{

                if(grade.getRoot()==1&&dto.getRoot()==1){
                    throw new ApplicationException("一个体系只能有一个根节点"+"["+grade.getDistributorGradeName()+"]");
                }
                dto.setRoot(0);
            });
        }

        return distributorGradeService.create(dto);
    }

    @Override
    public DistributorGradeDTO detail(Long gradeId) {

        log.info("查看经销商等级详情");

        //等级表
        DistributorGradeDTO gdto=distributorGradeService.getById(gradeId);

        if(null!=gdto){

            long parentId=gdto.getParentId();

            if(parentId>0){

                DistributorGradeDTO pdto= distributorGradeService.getById(parentId);

                if(null!=pdto){
                   gdto.setParent(pdto);
                }
            }
        }

        long systemId=gdto.getGradeSystemId();
        if(systemId>0){

            //体系表
            DistributorGradeSystemDTO sdto=distributorGradeSystemService.detail(systemId);

            gdto.setSystem(sdto);
        }

        return gdto;
    }

    @Override
    public List<DistributorGradeDTO> findPage(DistributorGradeQuery query) {

        log.info("经销商等级列表分页查询");

        //等级表数据
        List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(query);

        if(CollectionUtil.isEmpty(gradeList)){
            return Collections.emptyList();
        }

        //父级信息
        List<Long> parentIdList=gradeList.stream().map(DistributorGradeDTO::getParentId).collect(Collectors.toList());

        if(CollectionUtil.isNotEmpty(parentIdList)){

            DistributorGradeQuery parentQuery=new DistributorGradeQuery();

            parentQuery.setIds(parentIdList);

            List<DistributorGradeDTO> parentGradeList = distributorGradeService.findPage(parentQuery);

            if(CollectionUtil.isNotEmpty(parentGradeList)){

                Map<Long, List<DistributorGradeDTO>> parentGradeMap =
                        parentGradeList.stream().collect(Collectors.groupingBy(DistributorGradeDTO::getId));

                // 根据id设置父级编码和名称
                gradeList.forEach(grade -> {

                    List<DistributorGradeDTO> gradeDTOS = parentGradeMap.get(grade.getParentId());

                    if (CollectionUtil.isEmpty(gradeDTOS)) {

                        grade.setParentGradeCode("");

                        grade.setParentGradeName("");

                    } else {

                        //id是主键,只有一条记录
                        DistributorGradeDTO gdto = gradeDTOS.get(0);

                        grade.setParentGradeCode(gdto.getDistributorGradeCode() == null ? "" : gdto.getDistributorGradeCode());

                        grade.setParentGradeName(gdto.getDistributorGradeName()==null? "": gdto.getDistributorGradeName());
                    }
                });
            }
        }

        //体系表数据
        List<Long> systemIdList=gradeList.stream().map(DistributorGradeDTO::getGradeSystemId).
                collect(Collectors.toList());

        if(CollectionUtil.isNotEmpty(systemIdList)){

            DistributorGradeSystemQuery systemQry=new DistributorGradeSystemQuery();

            systemQry.setIds(systemIdList);

            List<DistributorGradeSystemDTO> systemList= distributorGradeSystemService.findPage(systemQry);

            Map<Long, List<DistributorGradeSystemDTO>> systemMap =

                    systemList.stream().collect(Collectors.groupingBy(DistributorGradeSystemDTO::getId));

            gradeList.forEach(grade -> {

                Long systemId=grade.getGradeSystemId();

                if(0!=systemId){

                    // 根据id对应设置体系编码
                    List<DistributorGradeSystemDTO> systemDTOS = systemMap.get(systemId);

                    if (CollectionUtil.isNotEmpty(systemDTOS)) {

                        DistributorGradeSystemDTO sdto=systemDTOS.get(0);

                        grade.setSystem(sdto);
                    }
                }
            });
        }
        return gradeList;
    }

    @Override
    public List<DistributorGradeDTO> findParentNodesForUpdate(Long systemId,Long gradeId) {

        List<DistributorGradeDTO> resultList=new ArrayList<>();

        //原来的体系只能返回原来的上级(根节点是没有上级的)
        DistributorGradeDTO self=distributorGradeService.getById(gradeId);

        Long origSystemId=self.getGradeSystemId();

        if(systemId==origSystemId){

            Long parentId=self.getParentId();

            if(parentId>0){

                DistributorGradeDTO dto = distributorGradeService.getById(parentId);

                resultList.add(dto);

                return resultList;
            }
        }

        //新体系返回最后一个等级
        DistributorGradeQuery query = new DistributorGradeQuery();

        query.setSystemId(systemId);

        List<DistributorGradeDTO> pageList = distributorGradeService.findPage(query);

        List<Long> parentIdList=pageList.stream().map(DistributorGradeDTO::getParentId).collect(Collectors.toList());


        pageList.forEach(grade->{

            if(!parentIdList.contains(grade.getId())){

                resultList.add(grade);
            }
        });

        return  resultList;
    }

    @Override
    public List<DistributorGradeDTO> findParentNodesForCreate(Long systemId) {

        if(0==systemId){
            return Collections.emptyList();
        }

        //等级表数据
        DistributorGradeQuery query=new DistributorGradeQuery();

        query.setSystemId(systemId);

        List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(query);

        List<DistributorGradeDTO> resultList=new ArrayList<>();

        if(CollectionUtil.isEmpty(gradeList)){
            return Collections.emptyList();
        }

        List<Long> parentIdList=gradeList.stream().map(DistributorGradeDTO::getParentId).collect(Collectors.toList());

        Map<Long, Long> parentMap =new HashMap<>();

        if(CollectionUtil.isNotEmpty(parentIdList)){

            parentIdList.forEach(pid->{
                parentMap.put(pid,pid);
            });

            gradeList.forEach(grade->{

                //没有下级的才可以挂载新子节点
                Long v=parentMap.get(grade.getId());

                //按体系查
                if(null==v&&grade.getGradeSystemId()==systemId){
                    resultList.add(grade);
                }
            });
        }

        return resultList;
    }

    @Override
    public List<DistributorGradeDTO> findAllGradesBySystem(long systemId) {

        log.info("查找经销商体系详细信息");

        DistributorGradeSystemDTO systemDTO = distributorGradeSystemService.detail(systemId);

        DistributorGradeQuery query = new DistributorGradeQuery();

        query.setSystemId(systemId);

        List<DistributorGradeDTO> pageList = distributorGradeService.findPage(query);

        List<DistributorGradeDTO> gradeList=new ArrayList<>();

        if(CollectionUtil.isNotEmpty(pageList)){

            pageList.forEach(page->{

                if(page.getGradeSystemId()==systemId){

                    page.setSystem(systemDTO);

                    gradeList.add(page);
                }
            });

        }
        return gradeList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(DistributorGradeDTO dto) {

        long id=dto.getId();

        DistributorGradeDTO origDTO=distributorGradeService.getById(id);

        long origSystemId=origDTO.getGradeSystemId();

        //根节点修改的冲突
        if(dto.getRoot()==1){

            Long systemId=origDTO.getGradeSystemId();

            DistributorGradeQuery query = new DistributorGradeQuery();

            query.setSystemId(systemId);

            findPage(query).forEach(grade->{

                if(grade.getRoot()==1){
                    throw new ApplicationException("已存在一个根节点"+"["+dto.getDistributorGradeName()+"]");
                }
            });
        }

        //改变所属体系
        long newSystemId=dto.getGradeSystemId();

        if(origSystemId==newSystemId){

            List<DistributorGradeDTO> children=distributorGradeService.listChildrenNodes(id);

            children.forEach(child->{
                child.setGradeSystemId(newSystemId);
            });

            distributorGradeService.updateBatchById(children);

        }
        return distributorGradeService.updateById(dto);
    }

    @Override
    public boolean delete(List<Long> gradeIdList) {

        log.info("经销商等级删除");

        if (CollectionUtils.isEmpty(gradeIdList)){
            return false;
        }

        //挂载经销商的不能删除
        log.info("查询关联经销商");
        List<DistributorGradeRelationDTO> dgrList = distributorGradeRelationService.findAllByGradeIds(gradeIdList);

        List<Long> distributorIds=new ArrayList<>();

        if(CollectionUtil.isNotEmpty(dgrList)){

           dgrList.forEach(dgr->{

               Long did=dgr.getDistributorId();

               if(did>0){

                   distributorIds.add(did);
               }
           });
        }

        if(CollectionUtil.isNotEmpty(distributorIds)){

            DistributorQuery query=new DistributorQuery();

            query.setIds(distributorIds);

            List<DistributorDTO> distributorList = distributorService.findPage(query);

            if(CollectionUtil.isNotEmpty(distributorList)){

                throw new ApplicationException("此等级已挂载经销商,不能删除!请解除所有关联后再操作");
            }
        }

        //有下级的不能删除
        log.info("查询下级");
        DistributorGradeQuery gradeQuery = new DistributorGradeQuery();

        gradeQuery.setIds(gradeIdList);

        List<DistributorGradeDTO> pageList = distributorGradeService.findPage(gradeQuery);

        if(CollectionUtil.isNotEmpty(pageList)) {

            Map<Long, DistributorGradeDTO> pageMap =
                    pageList.stream().collect(Collectors.toMap(DistributorGradeDTO::getId, a -> a,(k1,k2)->k1));

            pageList.forEach(page -> {

                gradeIdList.forEach(id -> {

                    if (page.getParentId().equals(id)) {

                        String childName= pageMap.get(page.getId()).getDistributorGradeName();

                        String parentName= pageMap.get(id).getDistributorGradeName();

                        throw new ApplicationException("["+parentName+"]已挂载 ["+childName+"],不能删除!请解除关联后再操作");
                    }
                });
            });
        }
        //删除等级
        log.info("开始删除等级");
        return distributorGradeService.delete(gradeIdList);
    }

}