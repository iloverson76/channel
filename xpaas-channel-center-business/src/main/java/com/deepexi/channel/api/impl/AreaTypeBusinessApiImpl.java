package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.AreaTypeBusinessApi;
import com.deepexi.channel.domain.AreaBusiDTO;
import com.deepexi.channel.domain.AreaTypeBusiDTO;
import com.deepexi.channel.domain.AreaTypeQuery;
import com.deepexi.channel.service.AreaTypeBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-27 15:32
 */
@RestController
public class AreaTypeBusinessApiImpl implements AreaTypeBusinessApi {

    @Autowired
    AreaTypeBusinessService areaTypeBusinessService;

    @Override
    public List<AreaBusiDTO> listLinkedAreas(Long pk) {
        return areaTypeBusinessService.listLinkedAreas ( pk );
    }

    @Override
    public List<AreaTypeBusiDTO> findPage(AreaTypeQuery query) {
        return areaTypeBusinessService.findPage ( query );
    }

    @Override
    public List<AreaTypeBusiDTO> getListAreaType(List<Long> ids) {
        return areaTypeBusinessService.getListAreaType ( ids );
    }

    @Override
    public List<AreaTypeBusiDTO> findParentAreaTypeByAreaId(Long areaId) {
        return areaTypeBusinessService.findParentAreaTypeByAreaId ( areaId );
    }

    @Override
    public boolean deleteAreaTypeByIds(List<Long> idList, Integer forceDelete) {
        return areaTypeBusinessService.deleteAreaTypeByIds ( idList, forceDelete);
    }

    @Override
    public boolean update(AreaTypeBusiDTO dto) {
        return areaTypeBusinessService.update ( dto );
    }

    @Override
    public List<AreaTypeBusiDTO> listParentNodesForUpdate(Long id) {
        return areaTypeBusinessService.listParentNodesForUpdate ( id );
    }

    @Override
    public List<AreaTypeBusiDTO> listParentNodesForCreate() {
        return areaTypeBusinessService.listParentNodesForCreate (  );
    }

    @Override
    public boolean deleteAreaTypeById(Long id) {
        return areaTypeBusinessService.deleteAreaTypeById ( id );
    }

    @Override
    public void validateHasAreas(List<Long> idList) {
        areaTypeBusinessService.validateHasAreas ( idList );
    }

    @Override
    public void validateHasChildren(List<Long> idList) {
        areaTypeBusinessService.validateHasChildren ( idList );
    }

    @Override
    public Long createAreaType(AreaTypeBusiDTO dto) {
        return areaTypeBusinessService.createAreaType ( dto );
    }

    @Override
    public void ValidateAareaTypeCode(String areaTypeCode) {
        areaTypeBusinessService.ValidateAareaTypeCode(areaTypeCode);
    }

    @Override
    public void validateAreaTypeName(String areaTypeName) {
        areaTypeBusinessService.validateAreaTypeName ( areaTypeName );
    }

    @Override
    public void validateAreaTypeNameEn(String areaTypeNameEn) {
        areaTypeBusinessService.validateAreaTypeNameEn ( areaTypeNameEn );
    }

    @Override
    public AreaTypeBusiDTO detail(Long id) {
        return areaTypeBusinessService.detail(id);
    }
}
