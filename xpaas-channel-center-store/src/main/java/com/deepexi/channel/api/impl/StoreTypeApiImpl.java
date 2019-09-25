package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.StoreTypeApi;
import com.deepexi.channel.domain.StoreTypeDTO;
import com.deepexi.channel.domain.StoreTypeQuery;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
        return null;
    }

    @Override
    public Long create(StoreTypeDTO dto) {
        return null;
    }

    @Override
    public Boolean delete(List<Long> ids) {
        return null;
    }

    @Override
    public boolean isCodeUnique(StoreTypeDTO dto) {
        return false;
    }

    @Override
    public boolean isNameUnique(StoreTypeDTO dto) {
        return false;
    }
}