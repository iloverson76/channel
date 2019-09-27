package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.ForceDeleteEnum;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.AreaBusinessService;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.AreaTypeBusinessService;
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

import java.beans.Transient;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AreaTypeBusinessServiceImpl implements AreaTypeBusinessService {

    @Autowired
    AreaService areaService;

    @Autowired
    AreaTypeService areaTypeService;

    @Autowired
    AreaBusinessService areaBusinessService;

    @Override
    public Long createAreaType(AreaTypeBusiDTO dto) {

        //编码不能重复
        ValidateAareaTypeCode(dto.getAreaTypeCode());

        //名称不能重复
        validateAreaTypeName(dto.getAreaTypeName());

        //设置处理(id路径)
        long newId=areaTypeService.saveAreaType(dto.clone ( AreaTypeDTO.class,CloneDirection.FORWARD ));

        long parentId=dto.getParentId();

        String path="";

        Long linkId=0L;

        if (0==parentId) {
            //首次创建
            path="/"+newId;

            linkId=newId;

        } else {

            AreaTypeDTO parent=areaTypeService.getById(parentId);

           path=parent.getPath()+"/"+newId;

            linkId=parent.getLinkId();
        }

        //链路
        dto.setId (newId);

        dto.setLinkId ( linkId );

        dto.setPath ( path );

        areaTypeService.updateAreaTypeById(dto.clone ( AreaTypeDTO.class,CloneDirection.FORWARD ));

        return newId;
    }

    @Override
    public void ValidateAareaTypeCode(String areaTypeCode){

        log.info("区域类型编码重复校验");

        List<String> codeList=areaTypeService.listAreaTypeCode();

        if(CollectionUtils.isNotEmpty(codeList)){

            codeList.forEach(typeCode->{

                if(areaTypeCode.equals(typeCode)){

                    throw new ApplicationException(ResultEnum.AREA_TYPE_CODE_DUPLICATED);
                }
            });
        }
    }

    @Override
    public void validateAreaTypeName(String areaTypeName) {

        log.info("区域类型中文名称重复校验");

        List<String> nameList=areaTypeService.listAreaTypeName();

        if(CollectionUtils.isNotEmpty(nameList)){

            nameList.forEach(typeName->{

                if(areaTypeName.equals(typeName)){

                    throw new ApplicationException(ResultEnum.AREA_TYPE_NAME_DUPLICATED);
                }
            });
        }

    }

    @Override
    public void validateAreaTypeNameEn(String areaTypeNameEn) {

        log.info("区域类型英文名称重复校验");

        List<String> nameEnList=areaTypeService.listAreaTypeNameEn();

        if(CollectionUtils.isNotEmpty(nameEnList)){

            nameEnList.forEach(typeNameEn->{

                if(areaTypeNameEn.equals(typeNameEn)){

                    throw new ApplicationException(ResultEnum.AREA_TYPE_NAME_EN_DUPLICATED);
                }
            });
        }

    }

    @Override
    public List<AreaBusiDTO> listLinkedAreas(Long pk) {

        log.info ( "层级元素列表查询" );

        //pk=0选全部
        if(pk<0){
            return Collections.emptyList();
        }

        List<AreaTypeDTO> typeList=areaTypeService.listLinkedAreas(pk);

        if(CollectionUtils.isEmpty(typeList)){
            return Collections.emptyList();
        }

        List<AreaTypeBusiDTO> typeBusiList = ObjectCloneUtils.convertList ( typeList, AreaTypeBusiDTO.class, CloneDirection.FORWARD );

        //区域信息(全部选回来再过滤)
        AreaQuery areaQuery=new AreaQuery();

        List<AreaDTO> areaDTOList=areaService.findPage(areaQuery);

        if(CollectionUtils.isEmpty(areaDTOList)){
            return Collections.emptyList();
        }

        List<AreaBusiDTO> areaBusiList=
                ObjectCloneUtils.convertList(areaDTOList,AreaBusiDTO.class,CloneDirection.FORWARD);

        Map<Long, List<AreaTypeBusiDTO>> typeListBusiMap =
                typeBusiList.stream().collect(Collectors.groupingBy(AreaTypeBusiDTO::getId));

         //过滤
        if(pk>0){
            List<AreaBusiDTO> filterAreaDTOList=new ArrayList<>();
            areaBusiList.forEach(orig->{
                if(pk==orig.getAreaTypeId()){
                    filterAreaDTOList.add(orig);
                }
            });
            areaBusiList=filterAreaDTOList;
        }

        areaBusiList.forEach(filter->{

            long typeId=filter.getAreaTypeId();

            List<AreaTypeBusiDTO> types=typeListBusiMap.get(typeId);

            if(CollectionUtils.isNotEmpty(types)){

                filter.setAreaType(types.get(0).clone ( AreaTypeDTO.class,CloneDirection.FORWARD ));
            }
        });

        areaBusinessService.setParentTypeId(areaBusiList);

        return areaBusiList;
    }

    @Override
    public List<AreaTypeBusiDTO> findPage(AreaTypeQuery query){

        log.info("区域类型分页查询");

        List<AreaTypeDTO> dtoList=areaTypeService.listAreaTypePage(query);

        if(CollectionUtils.isEmpty(dtoList)){

            return Collections.emptyList();
        }

        List<AreaTypeBusiDTO> dtoBusiList =
                ObjectCloneUtils.convertList ( dtoList, AreaTypeBusiDTO.class, CloneDirection.FORWARD );

        List<AreaTypeBusiDTO> childDTOList=ObjectCloneUtils.convertList(dtoList,AreaTypeBusiDTO.class,CloneDirection.FORWARD);

        for(AreaTypeBusiDTO parent:dtoBusiList){

            for (AreaTypeBusiDTO child:childDTOList){

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
    public List<AreaTypeBusiDTO> getListAreaType(List<Long> ids) {
        AreaTypeQuery query = new AreaTypeQuery();
        if (CollectionUtil.isEmpty(ids)){
            List<AreaTypeDTO> list = areaTypeService.listAreaTypePage(query);
            return ObjectCloneUtils.convertList ( list,AreaTypeBusiDTO.class,CloneDirection.FORWARD );
        }
        query.setIds(ids);
        List<AreaTypeDTO> list = areaTypeService.listAreaTypePage(query);
        log.info("区域链路",list);
        if (CollectionUtil.isEmpty(list)){
            return Collections.emptyList ();
        }

        List<AreaTypeBusiDTO> listBusi=
        ObjectCloneUtils.convertList ( list, AreaTypeBusiDTO.class,CloneDirection.FORWARD );

        Set<Long> set = new HashSet<>();
        for (AreaTypeBusiDTO AreaTypeBusiDTO : listBusi) {
            String path = AreaTypeBusiDTO.getPath();
            List<Long> idList = Arrays.stream(path.split("/")).filter(StringUtil::isNotEmpty).map(Long::parseLong).collect(Collectors.toList());
            set.add(idList.get(0));
        }
        List<Long> linkIdList = new ArrayList<>(set);
        List<AreaTypeDTO> areaTypeList = areaTypeService.findByAreaIdNotInLinkIdAll(linkIdList);

        if(CollectionUtils.isEmpty ( areaTypeList )){
            return Collections.emptyList ();
        }

        return ObjectCloneUtils.convertList ( areaTypeList,AreaTypeBusiDTO.class,CloneDirection.FORWARD );
    }

    @Override
    public List<AreaTypeBusiDTO> findParentAreaTypeByAreaId(Long areaId) {

        log.info("根据区域ID查找其上级分类");

        AreaDTO self = areaService.getAreaById(areaId);

        Long areaTypeId=self.getAreaTypeId();

        AreaTypeDTO type = areaTypeService.getAreaTypeById(areaTypeId);

        if(null==type){
            return Collections.emptyList ();
        }

        Long parentTypeId=type.getParentId();

        List<AreaTypeBusiDTO> resultList=new ArrayList<>();

        //限制上级分类
        if(type.getLimitParent()==1){

            AreaTypeDTO parentType = areaTypeService.getAreaTypeById(parentTypeId);

            resultList.add(parentType.clone ( AreaTypeBusiDTO.class,CloneDirection.FORWARD ));

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

            AreaTypeQuery query =new AreaTypeQuery();

            query.setIds (parentIds);

            List<AreaTypeDTO> typeList = areaTypeService.listAreaTypePage ( query );

            if(CollectionUtils.isNotEmpty ( typeList )){

                resultList=ObjectCloneUtils.convertList ( typeList,AreaTypeBusiDTO.class,CloneDirection.FORWARD );
            }
        }
        return resultList;
    }

    @Transient
    @Override
    public boolean deleteAreaTypeById(Long id){

        log.info("删除区域分类");

        //有区域挂载,不能删除
        AreaQuery query = new AreaQuery();

        List<Long> typeIdList=new ArrayList<>(1);

        typeIdList.add(id);

        query.setAreatypeIds(typeIdList);

        List<AreaDTO> areaList = areaService.findPage(query);

        if(CollectionUtil.isNotEmpty(areaList)){

           throw new ApplicationException("此分类已有区域挂载,无法删除!请解除所有关联后再操作");
        }

        //有下级,不能删除
        List<AreaTypeDTO> children=areaTypeService.listChildNodes("/"+id);

        if(CollectionUtil.isNotEmpty(children)){

            throw new ApplicationException("已有下级关联,不能删除!请解除关联后再操作");
        }

        return areaTypeService.deleteAreaTypeById(id);
    }

    @Transient
    @Override
    public boolean deleteAreaTypeByIds(List<Long> idList,Integer forceDelete) {

        log.info("批量删除区域分类");

        if(CollectionUtil.isEmpty(idList)){
            return false;
        }

        ForceDeleteEnum.validateIllegalForceDeleteFlag(forceDelete);

        //不强制删除=>走校验
        if(forceDelete == ForceDeleteEnum.NO.getCode()){

            validateHasAreas(idList);

            validateHasChildren(idList);
        }

        return areaTypeService.deleteAreaTypeByIds(idList);
    }

    @Override
    public boolean update(AreaTypeBusiDTO dto) {

        //校验
        Long id=dto.getId();

        List<Long> idList=new ArrayList<>(1);

        idList.add(id);

        validateHasAreas(idList);

        validateHasChildren(idList);

        //查询原来的数据
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
        return areaTypeService.updateAreaTypeById(dto.clone ( AreaTypeDTO.class,CloneDirection.FORWARD ));
    }

    private List<AreaTypeBusiDTO> listNoChildrenNodes(List<Long> idList) {

        log.info("查询没有下级的节点");

        AreaTypeQuery query = new AreaTypeQuery ();

        if(CollectionUtil.isNotEmpty(idList)){
            query.setIds(idList);
        }

        //没有被限制分类的节点
        List<AreaTypeDTO> dtoList = areaTypeService.listAreaTypePage(query);

        //首次创建处理空值
        if (CollectionUtils.isEmpty(dtoList)) {

            return Collections.emptyList();
        }

        List<Long> parentIdList = dtoList.stream().map(AreaTypeDTO::getParentId).collect(Collectors.toList());

        List<AreaTypeBusiDTO> resultList = new ArrayList<>();

        dtoList.forEach(dto -> {

            if (!parentIdList.contains(dto.getId())) {

                resultList.add(dto.clone ( AreaTypeBusiDTO.class,CloneDirection.FORWARD ));
            }
        });

        return resultList;
    }

    @Override
    public List<AreaTypeBusiDTO> listParentNodesForCreate() {

        log.info("创建区域分类接口:查询可用上级分类");

        return listNoChildrenNodes(null);
    }

    @Override
    public List<AreaTypeBusiDTO> listParentNodesForUpdate(Long id) {

        log.info("更新区域分类接口:查询可用上级分类");

        //没有下级的节点
        List<AreaTypeBusiDTO> noChildrenList= listNoChildrenNodes(null);

        if(CollectionUtils.isEmpty(noChildrenList)){
            return Collections.emptyList();
        }

        List<AreaTypeBusiDTO> resultList=ObjectCloneUtils.convertList ( noChildrenList,AreaTypeBusiDTO.class,CloneDirection.FORWARD );

        //移除自己
        List<AreaTypeBusiDTO> removeList= resultList.stream ().filter ( type -> type.getId () == id ).collect ( Collectors.toList () );

        resultList.removeAll ( removeList );

        //加上原来自己的上级
        AreaTypeDTO self=areaTypeService.getAreaTypeById(id);

        Long parentId=self.getParentId();

        if(parentId>0L){

            AreaTypeDTO parent=areaTypeService.getAreaTypeById(parentId);

            resultList.add(parent.clone (AreaTypeBusiDTO.class,CloneDirection.FORWARD ));
        }

        //移除所有子节点
        List<AreaTypeDTO> children=areaTypeService.listChildNodes("/"+id+"/");

        if(CollectionUtils.isNotEmpty(children)&&CollectionUtils.isNotEmpty ( resultList )) resultList.forEach ( type -> {

            children.stream ().filter ( child -> child.getId () == type.getId () ).map ( child -> type ).forEach ( removeList::add );

        } );

        resultList.removeAll ( removeList );

        if(CollectionUtils.isEmpty ( resultList )){
            return Collections.emptyList ();
        }

        return ObjectCloneUtils.convertList(resultList,AreaTypeBusiDTO.class);
    }

    public void updateChildrenPath(Long id,String replacedPath,String newPath){

        List<AreaTypeDTO> children=areaTypeService.listChildNodes(id+"/");

        if(CollectionUtils.isNotEmpty(children)){

            children.forEach(ado->{
                //替换新路径
                ado.setPath(ado.getPath().replaceAll(replacedPath,newPath));
            });
            areaTypeService.updateAreaTypeByIds(children);
        }
    }

    @Override
    public void validateHasChildren(List<Long> idList){

        log.info("校验分类是否有下级");

        //查询没有下级的节点
        List<AreaTypeBusiDTO> dtoList = listNoChildrenNodes(idList);

        if(CollectionUtil.isNotEmpty(dtoList)){

            dtoList.forEach(dto->{

                idList.forEach(id->{

                    if(id.equals(dto.getParentId())){
                        throw new ApplicationException("已有下级关联!请解除关联后再操作");
                    }
                });
            });
        }
    }

    @Override
    public void validateHasAreas(List<Long> idList){

        log.info("校验分类是否有区域关联");

        AreaQuery query = new AreaQuery();

        query.setAreatypeIds(idList);

        List<AreaDTO> areaList = areaService.findPage(query);

        if(CollectionUtil.isNotEmpty(areaList)){

            throw new ApplicationException("分类已有区域挂载!请解除关联后再操作");
        }
    }

    @Override
    public AreaTypeBusiDTO detail(Long id) {

        AreaTypeDTO dto=areaTypeService.getAreaTypeById(id);

        if (null==dto) {
            return new AreaTypeBusiDTO();
        }

        long parentId=dto.getParentId();

        if(parentId>0L){

            AreaTypeDTO parent=areaTypeService.getAreaTypeById(id);

            dto.setParentName(parent.getAreaTypeName());

            dto.setParentNameEn(parent.getAreaTypeNameEn());
        }

        return dto.clone ( AreaTypeBusiDTO.class,CloneDirection.FORWARD );
    }

}