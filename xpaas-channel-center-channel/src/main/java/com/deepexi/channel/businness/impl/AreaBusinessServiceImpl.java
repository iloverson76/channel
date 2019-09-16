package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.AreaBusinessService;
import com.deepexi.channel.domain.area.*;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.util.CollectionUtil;
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

                        AreaTypeDTO type = areaTypes.get(0);//id是主键,只有一条记录

                        area.setAreaType(type);
                    }
                });
            }
        }
        return areaDTOList;
    }

    @Override
    public AreaDTO detail(Long pk, Long areaTypeId) {

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

        return areaService.listLinkedAreasByType(areaTypeId);
    }

    @Override
    public List<AreaTreeDTO> listChildrenTree(Long areaId){

        List<AreaDTO> areaDTOList=areaService.listChildrenAreas(areaId);

        //区域类型
        setAreaTypeInfo(areaDTOList);

        //树构建
        return treeNode(areaDTOList,areaId);
    }

    @Override
    public List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query) {//默认展开三级:未完善

        List<AreaDTO> areaDTOList=areaService.findPage(new AreaQuery());

        //区域类型
        setAreaTypeInfo(areaDTOList);

        //树构建
        return treeAll(areaDTOList);
    }

    //区域类型设置
    private List<AreaDTO> setAreaTypeInfo( List<AreaDTO> areaDTOList){

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

    //从根节点构建完整树
    private List<AreaTreeDTO> treeAll(List<AreaDTO> areaDTOList){

        if(CollectionUtils.isEmpty(areaDTOList)){

            return Collections.emptyList();
        }

        List<AreaTreeDTO> list = ObjectCloneUtils.convertList(areaDTOList, AreaTreeDTO.class, CloneDirection.OPPOSITE);

        List<AreaTreeDTO> tree = new ArrayList<>();

        for (AreaTreeDTO vo1 : list) {

            if (vo1.getChildren() == null) {

                vo1.setChildren(new LinkedHashSet<>());
            }
            if (0 == vo1.getParentId()) {//根节点

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

        if(CollectionUtils.isEmpty(areaDTOList)){

            return Collections.emptyList();
        }

        List<AreaTreeDTO> list = ObjectCloneUtils.convertList(areaDTOList, AreaTreeDTO.class, CloneDirection.OPPOSITE);

        List<AreaTreeDTO> tree = new ArrayList<>();

        for (AreaTreeDTO vo1 : list) {

            if (vo1.getChildren() == null) {

                vo1.setChildren(new LinkedHashSet<>());
            }
            if (vo1.getParentId().equals(areaId)) {//根节点

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

        AreaDTO dto=areaService.getAreaById(id);

        String parentPath=dto.getPath();

        List<AreaDTO> children= areaService.listChildrenAreas(id);

        children.forEach(child->{

            String path=child.getPath();

            child.setPath(path.replaceAll(parentPath,""));

            //直接下级
            if(child.getParentId()==id){

                child.setParentId(-1L);
            }

        });

        areaService.updateBatch(children);

        areaService.deleteById(id);

        return Boolean.TRUE;
    }

    @Override
    public boolean update(AreaDTO dto) {

        areaService.update(dto);//未处理路径

        //处理路径

        return Boolean.TRUE;
    }

    @Override
    public boolean updateToRootNode(Long areaId) {

        AreaDTO area = areaService.getAreaById(areaId);

        String updatePath=area.getPath();

        String newRootPath="/"+areaId;

        //更新子节点路径
        List<AreaDTO> children = areaService.listChildrenAreas(areaId);

        if(CollectionUtils.isNotEmpty(children)){

            children.forEach(child->{

                String newPath=child.getPath().replaceAll(updatePath,newRootPath);

                child.setPath(newPath);
            });

            areaService.updateBatch(children);
        }



        area.setRoot(1);

        area.setPath(newRootPath);

        area.setParentId(0L);

        areaService.update(area);

        return Boolean.TRUE;
    }

    @Override
    public boolean updateTreeChange(Long newParentId,Long id,Integer root) {

        //自己
        AreaDTO self=areaService.getAreaById(id);

        //根节点处理
        if(root==1){
            String origPath=self.getPath();
            self.setParentId(0L);
            String rootPath="/"+id;
            self.setPath(rootPath);
            areaService.update(self);
            UpdateChildrenPath(id,origPath,rootPath);
        }else{

            //从新上级节点拼接新路径
            AreaDTO newParentNode=areaService.getAreaById(newParentId);

            String newParentPath=newParentNode.getPath();

            String updatePath=newParentPath+"/"+id;

            String replacepath=self.getPath();

            //自己
            self.setParentId(newParentId);
            self.setPath(updatePath);
            areaService.update(self);

            //子节点
            UpdateChildrenPath(id,replacepath,updatePath);
        }

        return Boolean.TRUE;
    }

    private void UpdateChildrenPath(Long id,String OrigParentPath,String updatePath){

        //更改所有子节点的路径
        List<AreaDTO> children = areaService.listChildrenAreas(id);

        if(CollectionUtils.isNotEmpty(children)){

            for(AreaDTO child:children){

                String path=child.getPath();

                child.setPath(path.replaceAll(OrigParentPath,updatePath));
            }

            areaService.updateBatch(children);
        }
    }


}