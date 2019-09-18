package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.AreaTypeBusinessService;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AreaTypeBusinessServiceImpl implements AreaTypeBusinessService {

    @Autowired
    AreaService areaService;

    @Autowired
    AreaTypeService areaTypeService;


    @Override
    public List<AreaDTO> listLinkedAreas(long pk) {

        //pk=0选全部
        if(pk<0){
            return Collections.emptyList();
        }

        List<AreaTypeDTO> typeList=areaTypeService.listLinkedAreas(pk);

        if(CollectionUtils.isEmpty(typeList)){
            return Collections.emptyList();
        }

        //区域信息(全部选回来再过滤)
        AreaQuery areaQuery=new AreaQuery();

        List<AreaDTO> areaDTOList=areaService.findPage(areaQuery);

        if(CollectionUtils.isEmpty(areaDTOList)){
            return Collections.emptyList();
        }

        Map<Long, List<AreaTypeDTO>> typeListMap =
                typeList.stream().collect(Collectors.groupingBy(AreaTypeDTO::getId));

         //过滤
        if(pk>0){
            List<AreaDTO> filterAreaDTOList=new ArrayList<>();
            areaDTOList.forEach(orig->{
                if(pk==orig.getAreaTypeId()){
                    filterAreaDTOList.add(orig);
                }
            });
            areaDTOList=filterAreaDTOList;
        }

        areaDTOList.forEach(filter->{

            long typeId=filter.getAreaTypeId();

            List<AreaTypeDTO> types=typeListMap.get(typeId);

            if(CollectionUtils.isNotEmpty(types)){

                filter.setAreaType(types.get(0));
            }
        });

        return areaDTOList;
    }


    @Override
    public List<AreaTypeDTO> findPage(AreaTypeQuery query){

        List<AreaTypeDTO> dtoList=areaTypeService.listAreaTypePage(query);

        if(CollectionUtils.isEmpty(dtoList)){

            return Collections.emptyList();
        }

        List<AreaTypeDTO> childDTOList=ObjectCloneUtils.convertList(dtoList,AreaTypeDTO.class,CloneDirection.FORWARD);

        for(AreaTypeDTO parent:dtoList){

            for (AreaTypeDTO child:childDTOList){

                if(parent.getId().equals(child.getParentId())){

                    child.setParentName(parent.getAreaTypeName());

                    child.setParentCode(parent.getAreaTypeCode());

                    child.setParentNameEn(parent.getParentNameEn());
                }
            }
        }

        return childDTOList;
    }

    @Override
    public List<AreaTypeDTO> getListAreaType(List<Long> ids) {
        AreaTypeQuery query = new AreaTypeQuery();
        if (CollectionUtil.isEmpty(ids)){
            List<AreaTypeDTO> list = areaTypeService.listAreaTypePage(query);
            return list;
        }
        query.setIds(ids);
        List<AreaTypeDTO> list = areaTypeService.listAreaTypePage(query);
        log.info("区域链路",list);
        if (CollectionUtil.isEmpty(list)){
            return new ArrayList<>();
        }
        Set<Long> set = new HashSet<>();
        for (AreaTypeDTO areaTypeDTO : list) {
            String path = areaTypeDTO.getPath();
            List<Long> idList = Arrays.stream(path.split("/")).filter(StringUtil::isNotEmpty).map(Long::parseLong).collect(Collectors.toList());
            set.add(idList.get(0));
        }
        List<Long> linkIdList = new ArrayList<>(set);
        List<AreaTypeDTO> areaTypeDTO = areaTypeService.findByAreaIdNotInLinkIdAll(linkIdList);
        return areaTypeDTO;
    }

    @Override
    public List<AreaTypeDTO> findParentAreaTypeByAreaId(Long areaId) {

        log.info("根据区域ID查找其上级分类");

        AreaDTO self = areaService.getAreaById(areaId);

        Long areaTypeId=self.getAreaTypeId();

        AreaTypeDTO type = areaTypeService.getAreaTypeById(areaTypeId);

        Long parentTypeId=type.getParentId();

        List<AreaTypeDTO> resultList=new ArrayList<>();

        //限制上级分类
        if(type.getLimitParent()==1){

            AreaTypeDTO parentType = areaTypeService.getAreaTypeById(parentTypeId);

            resultList.add(parentType);

        //不限制上级分类
        }else{

            String selfPath=type.getPath();

            if(type.getParentId()==0){
                return Collections.emptyList();
            }

            String parentPath=selfPath.replaceAll("/"+type.getId(),"");

            List<Long> parentIds=new ArrayList<>();

            String[] pids=parentPath.split("/");

            for (String pid:pids){

                if(!pid.equalsIgnoreCase("")){
                    parentIds.add(Long.valueOf(pid));
                }
            }

            AreaTypeQuery query = new AreaTypeQuery();

            query.setIds(parentIds);

            resultList=areaTypeService.listAreaTypePage(query);
        }

        return resultList;
    }

    @Override
    public boolean deleteAreaTypeByIds(List<Long> idList) {

        //挂载的区域
        AreaQuery query=new AreaQuery();

        query.setAreatypeIds(idList);

        List<AreaDTO> areaList = areaService.findPage(query);

        if(CollectionUtils.isNotEmpty(areaList)){

           throw new ApplicationException("此分类已挂载区域,无法删除!请解除所有关联后再操作!");
        }

        //删除分类
        return areaTypeService.deleteAreaTypeByIds(idList);
    }

    @Override
    public boolean update(AreaTypeDTO dto) {

        Long id=dto.getId();

        AreaTypeDTO self = areaTypeService.getAreaTypeById(id);

        Long origParentId=self.getParentId();

        Long newParentId=dto.getParentId();

        //如果挂载到新等级
        if(!newParentId.equals(origParentId)){

            AreaTypeDTO newParent=areaTypeService.getAreaTypeById(newParentId);

            String newParentPath =newParent.getPath();

            String origSelfPath= self.getPath();

            String newSelfPath=newParentPath+"/"+id;

            updateChildrenPath(id,origSelfPath,newSelfPath);
        }

        return areaTypeService.updateAreaTypeById(dto);
    }

    @Override
    public List<AreaTypeDTO> listParentNodesForCreate() {

        log.info("创建区域分类接口:查询可用上级分类");


        //没有被限制分类的节点
        List<AreaTypeDTO> dtoList1=areaTypeService.listAreaTypePage(new AreaTypeQuery());

        //首次创建处理空值
        if(CollectionUtils.isEmpty(dtoList1)){

            return Collections.emptyList();
        }

        List<AreaTypeDTO> dtoList2=ObjectCloneUtils.convertList(dtoList1,AreaTypeDTO.class,CloneDirection.FORWARD);

        List<AreaTypeDTO> resultList=new ArrayList<>();

        dtoList1.forEach(dto1->{

            dtoList2.forEach(dto2->{

                if(!dto1.getId().equals(dto2.getParentId())){

                    resultList.add(dto1);
                }
            });
        });

        return resultList;
    }

    @Override
    public List<AreaTypeDTO> listParentNodesForUpdate(Long id) {

        log.info("更新区域分类接口:查询可用上级分类");

        //没有上级的节点
        List<AreaTypeDTO> noParentNodeList= listParentNodesForCreate();

        if(CollectionUtils.isEmpty(noParentNodeList)){
            return Collections.emptyList();
        }
        //加上原来自己的上级
        AreaTypeDTO self=areaTypeService.getAreaTypeById(id);

        Long parentId=self.getParentId();

        if(parentId>0L){

            AreaTypeDTO parent=areaTypeService.getAreaTypeById(parentId);

            noParentNodeList.add(parent);
        }

        //不能选自己和自己的直接子节点
        List<AreaTypeDTO> children=areaTypeService.listChildNodes("/"+id);

        if(CollectionUtils.isNotEmpty(children)){

            noParentNodeList.removeAll(children);
        }

        return ObjectCloneUtils.convertList(noParentNodeList,AreaTypeDTO.class);
    }

    private void updateChildrenPath(Long id,String replacedPath,String newPath){

        List<AreaTypeDTO> children=areaTypeService.listChildNodes(id+"/");

        if(CollectionUtils.isNotEmpty(children)){

            children.forEach(ado->{
                //替换新路径
                ado.setPath(ado.getPath().replaceAll(replacedPath,newPath));
            });
            areaTypeService.updateAreaTypeByIds(children);
        }
    }

}