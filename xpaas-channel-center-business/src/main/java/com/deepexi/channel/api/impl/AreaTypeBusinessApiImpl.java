package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.AreaTypeBusinessApi;
import com.deepexi.channel.domain.AreaBusiDTO;
import com.deepexi.channel.domain.AreaTypeDTO;
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
    public List<AreaTypeDTO> findPage(AreaTypeQuery query) {
        return areaTypeBusinessService.findPage ( query );
    }

    @Override
    public List<AreaTypeDTO> getListAreaType(List<Long> ids) {
        return areaTypeBusinessService.getListAreaType ( ids );
    }

    @Override
    public List<AreaTypeDTO> findParentAreaTypeByAreaId(Long areaId) {
        return areaTypeBusinessService.findParentAreaTypeByAreaId ( areaId );
    }

    @Override
    public boolean deleteAreaTypeByIds(List<Long> idList, Integer forceDelete) {
        return areaTypeBusinessService.deleteAreaTypeByIds ( idList, forceDelete);
    }

    @Override
    public boolean update(AreaTypeDTO dto) {
        return areaTypeBusinessService.update ( dto );
    }

    @Override
    public List<AreaTypeDTO> listParentNodesForUpdate(Long id) {
        return areaTypeBusinessService.listParentNodesForUpdate ( id );
    }

    @Override
    public List<AreaTypeDTO> listParentNodesForCreate() {
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
    public Long createAreaType(AreaTypeDTO dto) {
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
    public AreaTypeDTO detail(Long id) {
        return areaTypeBusinessService.detail(id);
    }
}
