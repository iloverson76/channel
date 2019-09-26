package com.deepexi.channel.api.impl;

import api.AreaTypeApi;
import com.deepexi.channel.domain.AreaTypeDTO;
import com.deepexi.channel.domain.AreaTypeQuery;
import com.deepexi.channel.service.AreaTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 19:04
 */
public class AreaTypeApiImpl implements AreaTypeApi {

    @Autowired
    AreaTypeService areaTypeService;

    @Override
    public Long saveAreaType(AreaTypeDTO dto) {
        return areaTypeService.saveAreaType ( dto );
    }

    @Override
    public boolean updateAreaTypeById(AreaTypeDTO dto) {
        return areaTypeService.updateAreaTypeById ( dto );
    }

    @Override
    public boolean updateAreaTypeByIds(List<AreaTypeDTO> dtoList) {
        return areaTypeService.updateAreaTypeByIds ( dtoList );
    }

    @Override
    public boolean deleteAreaTypeById(Long id) {
        return areaTypeService.deleteAreaTypeById ( id );
    }

    @Override
    public boolean deleteAreaTypeByIds(List<Long> idList) {
        return areaTypeService.deleteAreaTypeByIds ( idList );
    }

    @Override
    public List<AreaTypeDTO> listAreaTypePage(AreaTypeQuery query) {
        return areaTypeService.listAreaTypePage ( query );
    }

    @Override
    public AreaTypeDTO getAreaTypeById(Long id) {
        return areaTypeService.getAreaTypeById ( id );
    }

    @Override
    public List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList) {
        return areaTypeService.listAreaTypeByIds ( areaTyeIdList );
    }

    @Override
    public List<AreaTypeDTO> listLinkedAreas(long pk) {
        return areaTypeService.listLinkedAreas ( pk );
    }

    @Override
    public List<AreaTypeDTO> findByAreaIdNotInLinkIdAll(List<Long> linkIdList) {
        return areaTypeService.findByAreaIdNotInLinkIdAll ( linkIdList );
    }

    @Override
    public AreaTypeDTO getById(Long id) {
        return areaTypeService.getById ( id );
    }

    @Override
    public List<AreaTypeDTO> listChildNodes(String pathStr) {
        return areaTypeService.listChildNodes ( pathStr );
    }

    @Override
    public List<String> listAreaTypeCode() {
        return areaTypeService.listAreaTypeCode ();
    }

    @Override
    public List<String> listAreaTypeName() {
        return areaTypeService.listAreaTypeName ();
    }

    @Override
    public List<String> listAreaTypeNameEn() {
        return areaTypeService.listAreaTypeNameEn ();
    }
}
