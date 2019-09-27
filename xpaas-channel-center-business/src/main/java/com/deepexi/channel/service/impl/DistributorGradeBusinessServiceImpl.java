package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.ForceDeleteEnum;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.*;
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
    public Long create(DistributorGradeBusiDTO busiDTO) {

        log.info("开始创建经销商等级");

        Long systemId=busiDTO.getGradeSystemId();

        //校验编码和名称是否重复
        validateDistributorGradeCode(busiDTO.getDistributorGradeCode(),systemId);

        validateDistributorGradeName(busiDTO.getDistributorGradeName(),systemId);

        //创建
        DistributorGradeQuery query = new DistributorGradeQuery();

        query.setSystemId(systemId);

        //按体系过滤数据
        List<DistributorGradeDTO> pageList = distributorGradeService.findPage(query);

        log.info("体系:"+systemId);

        if(CollectionUtil.isEmpty(pageList)){

            //首次新建必须是根节点,因为不是根节点的话上一级是必填的
            busiDTO.setRoot(1);

        }else{

            pageList.forEach(grade->{

                if(grade.getRoot()==1&&busiDTO.getRoot()==1){
                    throw new ApplicationException("一个体系只能有一个根节点"+"["+grade.getDistributorGradeName()+"]");
                }
            });
            busiDTO.setRoot(0);
        }

        return distributorGradeService.create(busiDTO.clone ( DistributorGradeDTO.class,CloneDirection.FORWARD ));
    }

    @Override
    public DistributorGradeBusiDTO detail(Long gradeId) {

        log.info("查看经销商等级详情");

        //等级表
        DistributorGradeDTO gdto=distributorGradeService.getById(gradeId);

        if(null==gdto){
            return new DistributorGradeBusiDTO();
        }

        DistributorGradeBusiDTO busiDTO=gdto.clone ( DistributorGradeBusiDTO.class,CloneDirection.FORWARD );

        long parentId=busiDTO.getParentId();

        if(parentId>0){

            DistributorGradeDTO pdto= distributorGradeService.getById(parentId);

            if(null!=pdto){
                busiDTO.setParent(pdto);
            }
        }

        long systemId=busiDTO.getGradeSystemId();
        if(systemId>0){

            //体系表
            DistributorGradeSystemDTO sdto=distributorGradeSystemService.detail(systemId);

            busiDTO.setSystem(sdto);
        }

        return busiDTO;
    }

    @Override
    public List<DistributorGradeBusiDTO> findPage(DistributorGradeQuery query) {

        log.info("经销商等级列表分页查询");

        //等级表数据
        List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(query);

        if(CollectionUtil.isEmpty(gradeList)){
            return Collections.emptyList();
        }

        List<DistributorGradeBusiDTO> gradeBusiList=
        ObjectCloneUtils.convertList ( gradeList, DistributorGradeBusiDTO.class,CloneDirection.FORWARD);

        //父级信息
        List<Long> parentIdList=gradeBusiList.stream().map(DistributorGradeBusiDTO::getParentId).collect(Collectors.toList());

        if(CollectionUtil.isNotEmpty(parentIdList)){

            DistributorGradeQuery parentQuery=new DistributorGradeQuery();

            parentQuery.setIds(parentIdList);

            List<DistributorGradeDTO> parentGradeList = distributorGradeService.findPage(parentQuery);

            if(CollectionUtil.isNotEmpty(parentGradeList)){

                Map<Long, List<DistributorGradeDTO>> parentGradeMap =
                        parentGradeList.stream().collect(Collectors.groupingBy(DistributorGradeDTO::getId));

                // 根据id设置父级编码和名称
                gradeBusiList.forEach(grade -> {

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
        List<Long> systemIdList=gradeBusiList.stream().map(DistributorGradeBusiDTO::getGradeSystemId).
                collect(Collectors.toList());

        if(CollectionUtil.isNotEmpty(systemIdList)){

            DistributorGradeSystemQuery systemQry=new DistributorGradeSystemQuery();

            systemQry.setIds(systemIdList);

            List<DistributorGradeSystemDTO> systemList= distributorGradeSystemService.findPage(systemQry);

            Map<Long, List<DistributorGradeSystemDTO>> systemMap =

                    systemList.stream().collect(Collectors.groupingBy(DistributorGradeSystemDTO::getId));

            gradeBusiList.forEach(grade -> {

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
        return gradeBusiList;
    }

    @Override
    public List<DistributorGradeBusiDTO> findParentNodesForUpdate(Long systemId,Long gradeId) {

        List<DistributorGradeBusiDTO> resultList=new ArrayList<>();

        //原来的体系只能返回原来的上级(根节点是没有上级的)
        DistributorGradeDTO self=distributorGradeService.getById(gradeId);

        Long origSystemId=self.getGradeSystemId();

        if(systemId==origSystemId){

            Long parentId=self.getParentId();

            if(parentId>0){

                DistributorGradeDTO parent = distributorGradeService.getById(parentId);

                resultList.add(parent.clone ( DistributorGradeBusiDTO.class,CloneDirection.FORWARD ));

                return resultList;
            }
        }else{

            //新体系返回最后一个等级
            DistributorGradeQuery query = new DistributorGradeQuery();

            query.setSystemId(systemId);

            List<DistributorGradeDTO> pageList = distributorGradeService.findPage(query);

            List<Long> parentIdList=pageList.stream().map(DistributorGradeDTO::getParentId).collect(Collectors.toList());

            pageList.forEach(grade->{

                if(!parentIdList.contains(grade.getId())){

                    resultList.add(grade.clone ( DistributorGradeBusiDTO.class,CloneDirection.FORWARD ));
                }
            });
        }
        return  resultList;
    }

    @Override
    public List<DistributorGradeBusiDTO> findParentNodesForCreate(Long systemId) {

        if(0==systemId){
            return Collections.emptyList();
        }

        //等级表数据
        DistributorGradeQuery query=new DistributorGradeQuery();

        query.setSystemId(systemId);

        List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(query);

        if(CollectionUtil.isEmpty(gradeList)){
            return Collections.emptyList();
        }

        List<DistributorGradeBusiDTO> resultList=new ArrayList<>();

        List<DistributorGradeBusiDTO> gradeBusiList=
        ObjectCloneUtils.convertList ( gradeList,DistributorGradeBusiDTO.class,CloneDirection.FORWARD );

        List<Long> parentIdList=gradeBusiList.stream().map(DistributorGradeBusiDTO::getParentId).collect(Collectors.toList());

        Map<Long, Long> parentMap =new HashMap<>();

        if(CollectionUtil.isNotEmpty(parentIdList)){

            parentIdList.forEach(pid->{
                parentMap.put(pid,pid);
            });

            gradeBusiList.forEach(grade->{

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
    public List<DistributorGradeBusiDTO> findAllGradesBySystem(long systemId) {

        log.info("查找经销商体系详细信息");

        DistributorGradeSystemDTO systemDTO = distributorGradeSystemService.detail(systemId);

        DistributorGradeQuery query = new DistributorGradeQuery();

        query.setSystemId(systemId);

        List<DistributorGradeDTO> pageList = distributorGradeService.findPage(query);

        List<DistributorGradeBusiDTO> gradeList=new ArrayList<>();

        if(CollectionUtil.isEmpty(pageList)){
            return Collections.emptyList ();
        }

        List<DistributorGradeBusiDTO> pageBusiList=ObjectCloneUtils.convertList ( pageList,
                DistributorGradeBusiDTO.class,CloneDirection.FORWARD);

        pageBusiList.forEach(page->{

            if(page.getGradeSystemId()==systemId){

                page.setSystem(systemDTO);

                gradeList.add(page);
            }
        });
        return gradeList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(DistributorGradeBusiDTO dto) {

        Long id=dto.getId();

        List<Long> gradeIdList=new ArrayList<>(1);

        gradeIdList.add(id);

        //有下级的不能修改
        validateHasChildren(gradeIdList);

        //挂载经销商的不能修改
        validateHasDistributors(gradeIdList);

        //查询原来的数据
        DistributorGradeDTO origDTO=distributorGradeService.getById(id);

        Long origSystemId=origDTO.getGradeSystemId();

        //改变所属体系
        Long newSystemId=dto.getGradeSystemId();

        if(origSystemId!=newSystemId){

            List<DistributorGradeBusiDTO> children=listChildrenNodes(id);

            if(CollectionUtils.isNotEmpty(children)){

                children.forEach(child->{
                    child.setGradeSystemId(newSystemId);
                });

                //更新等级表
                List<DistributorGradeDTO> childrenList=
                ObjectCloneUtils.convertList ( children,DistributorGradeDTO.class,CloneDirection.FORWARD);

                distributorGradeService.updateBatchById(childrenList);
            }

            //更新经销商关联表
            List<DistributorGradeRelationDTO> dgrList = distributorGradeRelationService.findAllByGradeId(id);

            if(CollectionUtils.isNotEmpty(dgrList)){

                dgrList.forEach(dgr->{
                    dgr.setSystemId(newSystemId);
                });
                distributorGradeRelationService.updateBatchById(dgrList);
            }
        }
        return distributorGradeService.updateById(dto.clone ( DistributorGradeDTO.class,CloneDirection.FORWARD ));
    }

    @Override
    public void validateDistributorGradeCode(String gradeCode,Long systemId) {

        log.info("经销商等级编码重复校验");

        List<String> codeList=distributorGradeService.listDistributorGradeCode(systemId);

        if(CollectionUtils.isNotEmpty(codeList)){

            codeList.forEach(code->{

                if(code.equals(gradeCode)){

                    throw new ApplicationException(ResultEnum.DISTRIBUTOR_GRADE_CODE_DUPLICATED);
                }
            });
        }
    }

    @Override
    public void validateDistributorGradeName(String gradeName,Long systemId) {

        log.info("经销商等级中文重复校验");

        List<String> nameList=distributorGradeService.listDistributorGradeName(systemId);

        if(CollectionUtils.isNotEmpty(nameList)){

            nameList.forEach(nameEn->{

                if(nameEn.equals(gradeName)){

                    throw new ApplicationException(ResultEnum.DISTRIBUTOR_GRADE_NAME_DUPLICATED);
                }
            });
        }
    }

    @Override
    public void validateDistributorGradeNameEn(String gradeNameEn,Long systemId) {

        log.info("经销商等级英文重复校验");

        List<String> nameEnList=distributorGradeService.listDistributorGradeNameEn(systemId);

        if(CollectionUtils.isNotEmpty(nameEnList)){

            nameEnList.forEach(nameEn->{

                if(nameEn.equals(gradeNameEn)){

                    throw new ApplicationException(ResultEnum.DISTRIBUTOR_GRADE_NAME_EN_DUPLICATED);
                }
            });
        }
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> gradeIdList,Integer forceDelete) {

        log.info("经销商等级删除");

        if (CollectionUtils.isEmpty(gradeIdList)){
            return false;
        }

        ForceDeleteEnum.validateIllegalForceDeleteFlag(forceDelete);

        if(forceDelete== ForceDeleteEnum.NO.getCode()){

            //有下级的不能删除
            validateHasChildren(gradeIdList);

            //挂载经销商的不能删除
            validateHasDistributors(gradeIdList);

        }else {

            deleteDistributors(gradeIdList);
        }

        log.info("开始删除等级");

        gradeIdList.forEach(id->{

            deleteById(id);
        });

        return true;
    }

    private boolean deleteById(long id) {

        List<DistributorGradeBusiDTO> children=listChildrenNodes(id);

        if(CollectionUtils.isNotEmpty(children)){

            children.forEach(child->{
                child.setParentId(0L);
            });

            distributorGradeService.updateBatchById(ObjectCloneUtils.convertList ( children,DistributorGradeDTO.class,CloneDirection.FORWARD ));
        }
        return distributorGradeService.deleteById(id);
    }

    @Override
    public void validateHasDistributors(List<Long> gradeIdList){

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

                throw new ApplicationException("此等级已挂载经销商!请解除所有关联后再操作");
            }
        }
    }

    @Override
    public Boolean deleteDistributors(List<Long> gradeIdList){

        List<DistributorGradeRelationDTO> dgrList = distributorGradeRelationService.findAllByGradeIds(gradeIdList);

        if(CollectionUtils.isEmpty(dgrList)){
            return true;
        }

        List<Long> distributorIdList = dgrList.stream().map(DistributorGradeRelationDTO::getDistributorId).collect(Collectors.toList());

        if(CollectionUtils.isEmpty(distributorIdList)){
            return true;
        }

       int result=distributorGradeRelationService.deleteBatchByDistributorIds(distributorIdList);

        if (result>0){
            return true;
        }
        return false;
    }

    @Override
    public void validateHasChildren(List<Long> gradeIdList){

        log.info("查询下级");

        List<DistributorGradeDTO> pageList = distributorGradeService.findPage(new DistributorGradeQuery());

        if(CollectionUtil.isEmpty(pageList)) {
            return;
        }

        List<DistributorGradeBusiDTO> pageBusiList=ObjectCloneUtils.convertList ( pageList,DistributorGradeBusiDTO.class,CloneDirection.FORWARD );

        Map<Long, DistributorGradeBusiDTO> pageMap =
                pageBusiList.stream().collect(Collectors.toMap(DistributorGradeBusiDTO::getId, a -> a,(k1,k2)->k1));

        pageBusiList.forEach(page -> {

            gradeIdList.stream().filter(id -> page.getParentId().equals(id)).forEach(id -> {

                String childName = pageMap.get(page.getId()).getDistributorGradeName();

                String parentName = pageMap.get(id).getDistributorGradeName();

                throw new ApplicationException("[" + parentName + "]已挂载 [" + childName + "]!请解除关联后再操作");
            });
        });
    }

    @Override
    public List<DistributorGradeBusiDTO> listChildrenNodes(Long id){

        log.info("查找等级下的子节点");

        if(id<=0){
            return Collections.emptyList();
        }

        List<DistributorGradeBusiDTO> dtoList=findPage(new DistributorGradeQuery());

        if(CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }

        List<DistributorGradeBusiDTO> children=new ArrayList<>();

        //查出所有子节点:新建的时候已经归属了某一个体系
        dtoList.forEach(dto->{

            if(dto.getParentId()==id){

                children.add(dto);
            }
        });
        return children;
    }

}