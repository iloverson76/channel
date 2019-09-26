package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.StoreTypeApi;
import com.deepexi.channel.domain.StoreTypeDTO;
import com.deepexi.channel.domain.StoreTypeQuery;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/25 17:29
 */
@Service
public class StoreTypeApiImpl implements StoreTypeApi {

    @Autowired
    StoreTypeService storeTypeService;

    @Override
    public List<StoreTypeDTO> listStoreType(StoreTypeQuery query) {
        return storeTypeService.findPage(query);
    }

    @Override
    public PageBean<StoreTypeDTO> listStoreTypePage(StoreTypeQuery query) {
        List<StoreTypeDTO> list = storeTypeService.findPage(query);
        return new PageBean<>(list);
    }

    @Override
    public StoreTypeDTO detail(@PathVariable(value = "id") Long pk) {
        return storeTypeService.detail(pk);
    }

    @Override
    public Boolean update(@PathVariable(value = "id") Long id, StoreTypeDTO dto) {
        dto.setId(id);
        return storeTypeService.update(dto);
    }

    @Override
    public Boolean updateBatch(@RequestBody List<StoreTypeDTO> dtos) {
        return storeTypeService.updateBatch(dtos);
    }

    @Override
    public Long create(@RequestBody StoreTypeDTO dto) {
        return storeTypeService.create(dto);
    }

    @Override
    public Boolean createBatch(List<StoreTypeDTO> dtos) {
        return storeTypeService.createBatch(dtos);
    }

    @Override
    public Boolean delete(@RequestBody List<Long> ids) {
        return storeTypeService.delete(ids);
    }

    @Override
    public boolean isCodeUnique(StoreTypeDTO dto) {
        return storeTypeService.isCodeUnique(dto);
    }

    @Override
    public boolean isNameUnique(StoreTypeDTO dto) {
        return storeTypeService.isNameUnique(dto);
    }
}