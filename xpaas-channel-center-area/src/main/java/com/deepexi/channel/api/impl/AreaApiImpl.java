package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.AreaApi;
import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaQuery;
import com.deepexi.channel.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 18:04
 */
@RestController
public class AreaApiImpl implements AreaApi {

    @Autowired
    AreaService areaService;

    @Override
    public Long create(AreaDTO dto) {
        return areaService.create ( dto );
    }

    @Override
    public boolean update(AreaDTO dto) {
        return areaService.update (dto );
    }

    @Override
    public List<AreaDTO> findPage(AreaQuery query) {
        return areaService.findPage ( query );
    }

    @Override
    public AreaDTO getAreaById(Long pk) {
        return areaService.getAreaById ( pk );
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        return areaService.deleteBatch ( ids );
    }

    @Override
    public boolean deleteById(Long id) {
        return areaService.deleteById ( id );
    }

    @Override
    public List<AreaDTO> listChildrenAreas(Long areaId) {
        return areaService.listChildrenAreas ( areaId );
    }

    @Override
    public List<AreaDTO> listLinkedAreasByType(Long areaTypeId) {
        return areaService.listLinkedAreasByType ( areaTypeId );
    }

    @Override
    public boolean updateBatch(List<AreaDTO> dtoList) {
        return areaService.updateBatch ( dtoList );
    }

    @Override
    public List<AreaDTO> findAllByIds(List<Long> ids) {
        return areaService.findAllByIds ( ids );
    }

    @Override
    public List<AreaDTO> findTree(Integer level) {
        return areaService.findTree (level);
    }

    @Override
    public List<String> listAreaCode() {
        return areaService.listAreaCode ();
    }

    @Override
    public List<String> listAreaName() {
        return areaService.listAreaName ();
    }

    @Override
    public List<String> listAreaNameEn() {
        return areaService.listAreaNameEn ();
    }
}
