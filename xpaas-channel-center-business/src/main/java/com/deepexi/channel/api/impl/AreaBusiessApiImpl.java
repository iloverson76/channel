package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.AreaBusinessApi;
import com.deepexi.channel.domain.*;
import com.deepexi.channel.service.AreaBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-27 15:02
 */
@RestController
public class AreaBusiessApiImpl implements AreaBusinessApi {

    @Autowired
    AreaBusinessService areaBusinessService;

    @Override
    public long create(AreaBusiDTO dto) {
        return areaBusinessService.create ( dto );
    }

    @Override
    public List<AreaBusiDTO> findPage(AreaQuery query) {
        return areaBusinessService.findPage ( query );
    }

    @Override
    public AreaBusiDTO detail(Long pk, Long areaTypeId) {
        return areaBusinessService.detail (pk, areaTypeId );
    }

    @Override
    public List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query) {
        return areaBusinessService.buildAreaTree ( query );
    }

    @Override
    public List<AreaTreeDTO> listChildrenTree(Long areaId) {
        return areaBusinessService.listChildrenTree ( areaId );
    }

    @Override
    public List<AreaBusiDTO> listLinkedAreasByType(Long areaTypeId) {
        return areaBusinessService.listLinkedAreasByType ( areaTypeId );
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids, Integer forDelete) {
        return areaBusinessService.deleteBatchByIds( ids,forDelete );
    }

    @Override
    public void validateHasChildren(List<Long> idList) {
        areaBusinessService.validateHasChildren ( idList );
    }

    @Override
    public void validateHasDistributors(List<Long> idList) {
        areaBusinessService.validateHasDistributors ( idList );
    }

    @Override
    public void validateHasStores(List<Long> idList) {
        areaBusinessService.validateHasStores ( idList );
    }

    @Override
    public void validateAreaOnTrea(List<Long> idList) {
        areaBusinessService.validateAreaOnTrea ( idList );
    }

    @Override
    public void validateAreaCode(String areaCode) {
        areaBusinessService.validateAreaCode ( areaCode );
    }

    @Override
    public void validateAreaName(String areaName) {
        areaBusinessService.validateAreaName ( areaName );
    }

    @Override
    public void validateAreaNameEn(String areaNameEn) {
        areaBusinessService.validateAreaNameEn ( areaNameEn );
    }

    @Override
    public boolean deleteChildren(List<Long> idList) {
        return areaBusinessService.deleteChildren ( idList );
    }

    @Override
    public boolean deleteDistributors(List<Long> idList) {
        return areaBusinessService.deleteDistributors ( idList );
    }

    @Override
    public boolean deleteStores(List<Long> idList) {
        return areaBusinessService.deleteStores ( idList );
    }

    @Override
    public boolean update(Long id, AreaBusiDTO dto) {
        return areaBusinessService.update ( id,dto );
    }

    @Override
    public boolean updateToRootNode(Long areaId) {
        return areaBusinessService.updateToRootNode ( areaId );
    }

    @Override
    public boolean treeAddNode(AreaDTO dto) {
        return areaBusinessService.treeAddNode ( dto );
    }

    @Override
    public boolean treeUpdateNode(AreaDTO dto) {
        return areaBusinessService.treeAddNode ( dto );
    }

    @Override
    public boolean treeDeleteNode(Long pk) {
        return areaBusinessService.treeDeleteNode ( pk );
    }

    @Override
    public void setParentTypeId(List<AreaBusiDTO> areaBusiList) {
        areaBusinessService.setParentTypeId ( areaBusiList );
    }
}
