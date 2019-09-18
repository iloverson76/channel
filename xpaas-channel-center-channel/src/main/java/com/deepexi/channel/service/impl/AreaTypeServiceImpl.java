package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.channel.dao.AreaTypeDAO;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.AreaTypeService;
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

/**
 * <p>
 * 区域类型-服务实现类
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@Service
@Slf4j
public class AreaTypeServiceImpl implements AreaTypeService {

    @Autowired
    AreaTypeDAO areaTypeDAO;

    AppRuntimeEnv appRuntimeEnv= AppRuntimeEnv.getInstance();

    @Transactional
    @Override
    public Long saveAreaType(AreaTypeDTO dto) {

        log.info("新增区域分类");
        if(null==dto){
            return 0L;
        }

        //编码不能重复
        ValidateAareaTypeCode(dto.getAreaTypeCode());

        //插入新节点
        setCommonColumns(dto);

        AreaTypeDO newNode=dto.clone(AreaTypeDO.class,CloneDirection.FORWARD);

        //save后,ado是插入数据库后返回的新数据,包括id,ado克隆要拆成两步写
        areaTypeDAO.save(newNode);

        //设置处理(id路径)
        long newId=newNode.getId();

        long parentId=newNode.getParentId();

        if (0==parentId) {

            newNode.setPath("/"+String.valueOf(newId));//首次创建

        } else {

            String parent_path=areaTypeDAO.getById(parentId).getPath()+"/"+newId;

            newNode.setPath(parent_path);
        }

        //链路
        long id=newNode.getId();

        newNode.setLinkId(id);

        areaTypeDAO.updateById(newNode);

        return id;
    }

    @Transactional
    @Override
    public boolean updateAreaTypeById(AreaTypeDTO dto) {

        if(null==dto){
            return false;
        }

       return areaTypeDAO.update(dto.clone(AreaTypeDO.class,CloneDirection.FORWARD));
    }

    @Override
    public boolean updateAreaTypeByIds(List<AreaTypeDTO> dtoList) {

        if(CollectionUtils.isEmpty(dtoList)){
            return false;
        }

        List<AreaTypeDO> eoList=ObjectCloneUtils.convertList(dtoList,AreaTypeDO.class,CloneDirection.FORWARD);

        return areaTypeDAO.updateBatchById(eoList);
    }

    private boolean deleteAreaTypeById(Long id){

        //有下级不能删除
        List<AreaTypeDTO> dtoList = listAreaTypePage(new AreaTypeQuery());

        for (AreaTypeDTO dto:dtoList){

            if(id.equals(dto.getParentId())){
                throw new ApplicationException("已被下级["+dto.getAreaTypeName()+"]关联,不能删除!");
            }
        }

        //删除区域类型
        return areaTypeDAO.removeById(id);
    }

    @Transactional
    @Override
    public boolean deleteAreaTypeByIds(List<Long> idList) {

        log.info("批量删除区域类型");

        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }

        boolean result=false;

        for (Long id : idList) {
            result=deleteAreaTypeById(id);
        }

        return result;
    }

    @Override
    public AreaTypeDTO getAreaTypeById(Long id) {

        AreaTypeDO ado=areaTypeDAO.getOne(getQueryWrapper(id));

        if (null==ado) {
            return new AreaTypeDTO();
        }

        AreaTypeDTO node = ado.clone(AreaTypeDTO.class,CloneDirection.OPPOSITE);

        long parentId=node.getParentId();

        if(0!=parentId){

            AreaTypeDO parent=areaTypeDAO.getOne(getQueryWrapper(parentId));

            node.setParentName(parent.getAreaTypeName());

            node.setParentNameEn(parent.getAreaTypeNameEn());
        }

        return node;
    }

    @Override
    public List<AreaTypeDTO> listAreaTypePage(AreaTypeQuery query) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<AreaTypeDO> typeList = areaTypeDAO.listAreaTypePage(query);

        return ObjectCloneUtils.convertList(typeList, AreaTypeDTO.class, CloneDirection.OPPOSITE);
    }


    @Override
    public List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList) {

        return areaTypeDAO.listAreaTypeByIds(areaTyeIdList);
    }

    @Override
    public List<AreaTypeDTO> listLinkedAreas(long pk) {

        if(pk<0){
            return Collections.emptyList();
        }

        List<AreaTypeDO> eoList=areaTypeDAO.listLinkedAreas(pk);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList,AreaTypeDTO.class,CloneDirection.OPPOSITE);
    }

    private QueryWrapper<AreaTypeDO> getQueryWrapper(Long id){

        QueryWrapper<AreaTypeDO> wp=new QueryWrapper<>();

        if(null!=id){
            wp.eq("id",id);
        }

        wp.eq("tenant_id",appRuntimeEnv.getTenantId());

        wp.eq("app_id",appRuntimeEnv.getAppId());

        return wp;
    }

    private void ValidateAareaTypeCode(String areaTypeCode){

        List<String> doList=areaTypeDAO.listAreaTypeCode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        if(CollectionUtils.isNotEmpty(doList)){
            doList.forEach(typeCode->{
                if(areaTypeCode.equals(typeCode)){
                    throw new ApplicationException(ResultEnum.AREA_TYPE_DUPLICATED);
                }
            });
        }
    }

    public void setCommonColumns(AreaTypeDTO dto){

    }

    @Override
    public List<AreaTypeDTO> findByAreaIdNotInLinkIdAll(List<Long> linkIdList) {
        List<AreaTypeDO> list = areaTypeDAO.findByAreaIdNotInLinkIdAll(linkIdList);
        List<AreaTypeDTO> areaTypeDTOList = ObjectCloneUtils.convertList(list, AreaTypeDTO.class, CloneDirection.OPPOSITE);
        return areaTypeDTOList;
    }

    @Override
    public AreaTypeDTO getById(Long id) {

        if(id<=0){
            return null;
        }

        AreaTypeDO eo = areaTypeDAO.getById(id);

        if(eo!=null){

            return eo.clone(AreaTypeDTO.class,CloneDirection.OPPOSITE);
        }

        return null;
    }

    @Override
    public List<AreaTypeDTO> listChildNodes(String idPath) {

        List<AreaTypeDO> eoList = areaTypeDAO.listChildNodes(idPath);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        List<AreaTypeDTO> dtoList=ObjectCloneUtils.convertList(eoList,AreaTypeDTO.class,CloneDirection.OPPOSITE);

        return dtoList;
    }
}
