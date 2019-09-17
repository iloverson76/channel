package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.AreaBusinessService;
import com.deepexi.channel.domain.area.*;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
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

    @Transient
    @Override
    public long create(AreaDTO dto) {

        long result= areaService.create(dto);

        return result;
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

    @Override
    public boolean deleteBatch(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return false;
        }

        ids.forEach(id->{
            deleteById(id);
        });

        return Boolean.TRUE;
    }

    @Override
    public boolean deleteById(Long id) {

        log.info("删除区域");

        AreaDTO dto=areaService.getAreaById(id);

        String parentPath=dto.getPath();

        List<AreaDTO> children= areaService.listChildrenAreas(id);

        children.forEach(child->{

            String path=child.getPath();

            child.setPath(path.replaceAll(parentPath,""));

            //直接下级
            if(child.getParentId()==id){

                child.setParentId(0L);
            }

        });

        areaService.updateBatch(children);

        areaService.deleteById(id);

        return Boolean.TRUE;
    }

    @Override
    public boolean update(AreaDTO dto) {

        log.info("更新区域");

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

        Long origParentId=self.getParentId();

        String path=self.getPath();

        //如果是根节点
        if(origParentId==0&& StringUtil.isEmpty(path)&&newParentId==0){

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

            //子节点
            updateChildrenNodesPath(id,replacepath,newParentPath);
        }

        return Boolean.TRUE;
    }

    @Override
    public boolean treeDeleteNode(Long areaId) {

        log.info("删除区域树节点");
        AreaDTO self=areaService.getAreaById(areaId);

        Long parentId=self.getParentId();

        AreaDTO parent=areaService.getAreaById(parentId);

        String parentPath=parent.getPath();

        String newParentPath="";

        updateChildrenNodesPath(areaId,parentPath,newParentPath);

        self.setPath("");

        self.setParentId(0L);

        return areaService.update(self);
    }

}