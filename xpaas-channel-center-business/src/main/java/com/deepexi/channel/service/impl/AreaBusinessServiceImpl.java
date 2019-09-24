package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.ForceDeleteEnum;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AreaBusinessServiceImpl implements AreaBusinessService {

    @Autowired
    AreaService areaService;

    @Autowired
    AreaTypeService areaTypeService;

    @Autowired
    StoreAreaService storeAreaService;

    @Autowired
    DistributorAreaRelationService distributorAreaRelationService;

    @Transient
    @Override
    public long create(AreaDTO dto) {

        validateAreaCode(dto.getAreaCode());

        validateAreaName(dto.getAreaName());

        Long result= areaService.create(dto);

        return result;
    }

    @Override
    public void validateAreaCode(String areaCode) {

        log.info("区域编码重复校验");

        List<String> codeList=areaService.listAreaCode();

        if(CollectionUtils.isNotEmpty(codeList)){

            codeList.forEach(code->{

                if(code.equals(areaCode)){

                    throw new ApplicationException(ResultEnum.AREA_CODE_DUPLICATED);
                }
            });
        }
    }

    @Override
    public void validateAreaName(String areaName) {

        log.info("区域中文名称重复校验");

        List<String> nameList=areaService.listAreaName();

        if(CollectionUtils.isNotEmpty(nameList)){

            nameList.forEach(name->{

                if(name.equals(areaName)){

                    throw new ApplicationException(ResultEnum.AREA_NAME_DUPLICATED);
                }
            });
        }
    }

    @Override
    public void validateAreaNameEn(String areaNameEn) {

        log.info("区域英文名称重复校验");

        List<String> nameEnList=areaService.listAreaNameEn();

        if(CollectionUtils.isNotEmpty(nameEnList)){

            nameEnList.forEach(nameEn->{

                if(nameEn.equals(areaNameEn)){

                    throw new ApplicationException(ResultEnum.AREA_NAME_EN_DUPLICATED);
                }
            });
        }
    }

    @Override
    public List<AreaDTO> findPage(AreaQuery query) {

        log.info("区域列表分页查询");

        if(null==query){
            return Collections.emptyList();
        }

         List<AreaDTO> areaDTOList=areaService.findPage(query);

        //区域类型信息
        List<Long> areaTyeIdList=areaDTOList.stream().map(AreaDTO::getAreaTypeId).collect(Collectors.toList());

        if(CollectionUtils.isNotEmpty(areaTyeIdList)){

            List<AreaTypeDTO> AreaTypeList=areaTypeService.listAreaTypeByIds(areaTyeIdList);

            if(CollectionUtils.isNotEmpty(AreaTypeList)){

                Map<Long, List<AreaTypeDTO>> areaTypeMap =
                        AreaTypeList.stream().collect(Collectors.groupingBy(AreaTypeDTO::getId));

                areaDTOList.forEach(area->{

                    List<AreaTypeDTO> areaTypes=areaTypeMap.get(area.getAreaTypeId());

                    if (CollectionUtil.isNotEmpty(areaTypes)) {

                        //id是主键,只有一条记录
                        AreaTypeDTO type = areaTypes.get(0);

                        area.setAreaType(type);
                    }
                });
            }
        }
        return areaDTOList;
    }

    @Override
    public AreaDTO detail(Long pk, Long areaTypeId) {

        log.info("区域详情");

        AreaDTO areaDTO=areaService.getAreaById(pk);

        if(null==areaDTO){
            return new AreaDTO();
        }

        AreaTypeDTO typeDTO=areaTypeService.getAreaTypeById(areaTypeId);

        if(null!=typeDTO){
            areaDTO.setAreaType(typeDTO);
        }

        return areaDTO;
    }

    @Override
    public List<AreaDTO> listLinkedAreasByType(Long areaTypeId) {

        log.info("根据区域类型查找所有的区域");

        return areaService.listLinkedAreasByType(areaTypeId);
    }

    @Override
    public List<AreaTreeDTO> listChildrenTree(Long areaId){

        log.info("构建节点树");

        List<AreaDTO> areaDTOList=areaService.listChildrenAreas(areaId);

        //区域类型
        setAreaTypeInfo(areaDTOList);

        //树构建
        return treeNode(areaDTOList,areaId);
    }

    @Override
    public List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query) {

        log.info("区域树构建");

        //默认展开三级
        List<AreaDTO> areaDTOList=areaService.findTree();

        //区域类型
        setAreaTypeInfo(areaDTOList);

        //树构建
        return treeAll(areaDTOList);
    }

    //区域类型设置
    private List<AreaDTO> setAreaTypeInfo( List<AreaDTO> areaDTOList){

        log.info("设置区域类型");

        if(CollectionUtils.isEmpty(areaDTOList)){

            return Collections.emptyList();
        }

        List<AreaTypeDTO> typeDTOS = areaTypeService.listAreaTypePage(new AreaTypeQuery());

        if(CollectionUtils.isNotEmpty(typeDTOS)){

            Map<Long,AreaTypeDTO> typeDTOMap=typeDTOS.stream().collect(Collectors.toMap(AreaTypeDTO::getId,c->c));

            areaDTOList.forEach(area->{

                AreaTypeDTO typeDTO = typeDTOMap.get(area.getAreaTypeId());

                if(typeDTO!=null){

                    area.setAreaType(typeDTO);
                }
            });
        }

        return areaDTOList;
    }

    private List<AreaTreeDTO> treeAll(List<AreaDTO> areaDTOList){

        log.info("从根节点构建完整树");

        if(CollectionUtils.isEmpty(areaDTOList)){

            return Collections.emptyList();
        }

        List<AreaTreeDTO> list = ObjectCloneUtils.convertList(areaDTOList, AreaTreeDTO.class, CloneDirection.OPPOSITE);

        List<AreaTreeDTO> tree = new ArrayList<>();

        for (AreaTreeDTO vo1 : list) {

            if (vo1.getChildren() == null) {

                vo1.setChildren(new LinkedHashSet<>());
            }

            //根节点
            if (0 == vo1.getParentId()&&vo1.getPath().equals("/"+vo1.getId())) {

                tree.add(vo1);
            }
            for (AreaTreeDTO vo2 : list) {

                if (vo2.getParentId()!=null&&0 != vo2.getParentId()&& vo2.getParentId().equals(vo1.getId())) {

                    if (vo1.getChildren() == null) {

                        vo1.setChildren(new LinkedHashSet<>());
                    }
                    vo1.getChildren().add(vo2);
                }
            }
        }
        return tree;
    }

    //从某一节点开始构建下级数
    private List<AreaTreeDTO> treeNode(List<AreaDTO> areaDTOList,Long areaId){

        log.info("开始构建节点树");

        if(CollectionUtils.isEmpty(areaDTOList)){

            return Collections.emptyList();
        }

        List<AreaTreeDTO> list = ObjectCloneUtils.convertList(areaDTOList, AreaTreeDTO.class, CloneDirection.OPPOSITE);

        List<AreaTreeDTO> tree = new ArrayList<>();

        for (AreaTreeDTO vo1 : list) {

            if (vo1.getChildren() == null) {

                vo1.setChildren(new LinkedHashSet<>());
            }
            //根节点
            if (0 == vo1.getParentId()&&vo1.getPath().equals("/"+vo1.getId())) {

                tree.add(vo1);
            }
            for (AreaTreeDTO vo2 : list) {

                if (vo2.getParentId()!=null&&0 != vo2.getParentId() && vo2.getParentId().equals(vo1.getId())) {

                    if (vo1.getChildren() == null) {

                        vo1.setChildren(new LinkedHashSet<>());
                    }
                    vo1.getChildren().add(vo2);
                }
            }
        }
        return tree;
    }

    @Transient
    @Override
    public boolean deleteBatchByIds(List<Long> ids,Integer forceDelete) {

        if(CollectionUtils.isEmpty(ids)){
            return false;
        }

        ForceDeleteEnum.validateIllegalForceDeleteFlag(forceDelete);

        if(forceDelete==ForceDeleteEnum.NO.getCode()){

            validateHasChildren(ids);

            validateHasStores(ids);

            validateHasDistributors(ids);

        }else{

            deleteChildren(ids);

            deleteStores(ids);

            deleteDistributors(ids);
        }

        return areaService.deleteBatch(ids);
    }

    @Override
    public void validateHasChildren(List<Long> idList){

        List<AreaDTO> children = getChildrenByIds(idList);

        if(CollectionUtils.isNotEmpty(children)){

            throw new ApplicationException("已有子区域关联!请解除关联后再操作");
        }
    }

    @Override
    public boolean deleteChildren(List<Long> idList){

        if(CollectionUtils.isNotEmpty(idList)){

            idList.forEach(id->{

                deleteChild(id);
            });
        }
        return true;
    }

    @Override
    public void validateHasDistributors(List<Long> areaIdList){

        List<DistributorAreaRelationDTO> darList = distributorAreaRelationService.findAllByAreaIds(areaIdList);

        if(CollectionUtils.isNotEmpty(darList)){

            throw new ApplicationException("已有经销商关联!请解除所有关联后再操作");
        }
    }

    @Override
    public boolean deleteDistributors(List<Long> areaIdList){

        List<DistributorAreaRelationDTO> darList = distributorAreaRelationService.findAllByAreaIds(areaIdList);

        int result=0;

        if(CollectionUtils.isNotEmpty(darList)){

            result=distributorAreaRelationService.deleteBatchByAreaIds(areaIdList);
        }

        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public void validateHasStores(List<Long> areaIdList){

        StoreAreaQuery query = new StoreAreaQuery();

        query.setAreaIds(areaIdList);

        List<StoreAreaDTO> pageList = storeAreaService.findList(query);

        if(CollectionUtils.isNotEmpty(pageList)){

          throw new ApplicationException("已有门店关联!请解除关联后再操作");
        }
    }

    @Override
    public boolean deleteStores(List<Long> ids){

        StoreAreaQuery query = new StoreAreaQuery();

        query.setAreaIds(ids);

        List<StoreAreaDTO> pageList = storeAreaService.findList(query);

        if(CollectionUtils.isNotEmpty(pageList)){

            List<Long> storeIdList = pageList.stream().map(StoreAreaDTO::getId).collect(Collectors.toList());

            return storeAreaService.removeByStoreIds(storeIdList);
        }
        return true;
    }

    public List<AreaDTO> getChildrenByIds(List<Long> idList){

        AreaQuery areaQuery = new AreaQuery();

        areaQuery.setIds(idList);

        List<AreaDTO> pageList1 = areaService.findPage(areaQuery);

        List<AreaDTO> children=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(pageList1)){

            List<AreaDTO> pageList2=ObjectCloneUtils.convertList(pageList1,AreaDTO.class,CloneDirection.FORWARD);

           pageList1.forEach(parent->{

               pageList2.forEach(child->{

                   if(child.getParentId().equals(parent.getId())){

                        children.add(child);
                   }

               });
           });
        }
        return  children;
    }

    private boolean deleteChild(Long parentId){

        AreaDTO dto=areaService.getAreaById(parentId);

        String parentPath=dto.getPath();

        List<AreaDTO> children= areaService.listChildrenAreas(parentId);

        if(CollectionUtil.isNotEmpty(children)){

            children.forEach(child->{

                String path=child.getPath();

                child.setPath(path.replaceAll(parentPath,""));

                //直接下级
                if(child.getParentId()==parentId){

                    //父级节点被删除
                    child.setParentId(-1L);
                }
            });
        }
        return areaService.updateBatch(children);
    }

    @Override
    public boolean update(AreaDTO dto) {

        log.info("更新区域");

        Long id=dto.getId();

        List<Long> ids=new ArrayList<>(1);

        ids.add(id);

        validateHasChildren(ids);

        validateHasStores(ids);

        validateHasDistributors(ids);

        areaService.update(dto);

        return Boolean.TRUE;
    }

    @Override
    public boolean updateToRootNode(Long areaId) {

        log.info("更改为根节点");

        AreaDTO self = areaService.getAreaById(areaId);

        String updatePath=self.getPath();

        String newRootPath="/"+areaId;

        //更新子节点路径
        updateChildrenNodesPath(areaId,updatePath,newRootPath);

        //更新自己
        self.setPath(newRootPath);

        self.setParentId(0L);

        areaService.update(self);

        return Boolean.TRUE;
    }

    private void updateChildrenNodesPath(Long areaId,String updatePath,String newRootPath){

        log.info("更新子节点路径");

        List<AreaDTO> children = areaService.listChildrenAreas(areaId);

        if(CollectionUtils.isNotEmpty(children)){

            children.forEach(child->{

                String newPath=child.getPath().replaceAll(updatePath,newRootPath);

                child.setPath(newPath);
            });

            areaService.updateBatch(children);
        }
    }

    @Override
    public boolean treeAddNode(AreaDTO dto) {

        log.info("新增区域树节点");

        Long areaId=dto.getId();

        Long newParentId=dto.getParentId();

        AreaDTO self = areaService.getAreaById(areaId);

        String path=self.getPath();

        //如果是根节点
        if(StringUtil.isEmpty(path)&&newParentId==0){

           self.setParentId(0L);

           self.setPath("/"+areaId);

           areaService.update(self);

            return Boolean.TRUE;
        }

        //挂载到新节点下
        if(null!=newParentId&&newParentId>0L){

            //从新上级节点拼接新路径
            AreaDTO newParentNode=areaService.getAreaById(newParentId);

            String newParentPath=newParentNode.getPath();

            String updatePath=newParentPath+"/"+areaId;

            //自己
            self.setParentId(newParentId);
            self.setPath(updatePath);
            areaService.update(self);
        }

        return Boolean.TRUE;
    }

    @Override
    public boolean treeUpdateNode(AreaDTO dto) {

        log.info("更改区域树节点");

        Long id=dto.getId();

        Long newParentId=dto.getParentId();

        //自己
        AreaDTO self=areaService.getAreaById(id);

        Long origParentId=self.getParentId();

        String path=self.getPath();

        //根节点处理
        if(origParentId!=0&&newParentId==0&&StringUtil.isNotEmpty(path)){

            updateToRootNode(id);

        }else{
            //从新上级节点拼接新路径
            AreaDTO newParentNode=areaService.getAreaById(newParentId);

            String newParentPath=newParentNode.getPath();

            newParentPath=newParentPath+"/"+id;

            String replacepath=self.getPath();

            //自己
            self.setParentId(newParentId);
            self.setPath(newParentPath);
            areaService.update(self);

            //子节点及孙子节点
            updateChildrenNodesPath(id,replacepath,newParentPath);
        }

        return Boolean.TRUE;
    }

    @Override
    public boolean treeDeleteNode(Long areaId) {

        log.info("删除区域树节点");

        AreaDTO self=areaService.getAreaById(areaId);

        List<AreaDTO> children = areaService.listChildrenAreas(areaId);

        if(CollectionUtil.isNotEmpty(children)){

            children.add(self);

            children.forEach(child->{

                child.setPath("");

                child.setParentId(0L);
            });

            areaService.updateBatch(children);
        }

        //更新自己
        self.setPath("");

        self.setParentId(0L);

        return areaService.update(self);
    }

}