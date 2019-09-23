package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.channel.dao.AreaTypeDAO;
import com.deepexi.channel.domain.AreaTypeDO;
import com.deepexi.channel.domain.AreaTypeDTO;
import com.deepexi.channel.domain.AreaTypeQuery;
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

import java.awt.geom.Area;
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

    @Transactional
    @Override
    public Long saveAreaType(AreaTypeDTO dto) {

        log.info("新增区域分类");
        if(null==dto){
            return 0L;
        }

       AreaTypeDO eo=dto.clone(AreaTypeDO.class,CloneDirection.FORWARD);

        areaTypeDAO.save(eo);

        return eo.getId();
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

    @Override
    public boolean deleteAreaTypeById(Long id){

        return areaTypeDAO.removeById(id);
    }

    @Transactional
    @Override
    public boolean deleteAreaTypeByIds(List<Long> idList) {

        log.info("批量删除区域类型");

        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }

        return areaTypeDAO.removeByIds(idList);
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

        return wp;
    }

    @Override
    public void ValidateAareaTypeCode(String areaTypeCode){

        List<String> doList=areaTypeDAO.listAreaTypeCode();

        if(CollectionUtils.isNotEmpty(doList)){
            doList.forEach(typeCode->{
                if(areaTypeCode.equals(typeCode)){
                    throw new ApplicationException(ResultEnum.AREA_TYPE_DUPLICATED);
                }
            });
        }
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
