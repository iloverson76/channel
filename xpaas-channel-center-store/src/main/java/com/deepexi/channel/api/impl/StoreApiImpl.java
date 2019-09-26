package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.StoreApi;
import com.deepexi.channel.domain.StoreDTO;
import com.deepexi.channel.domain.StoreQuery;
import com.deepexi.channel.service.StoreService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/25 20:36
 */
@Service
public class StoreApiImpl implements StoreApi {

    @Autowired
    StoreService storeService;

    @Override
    public List<StoreDTO> listStore(StoreQuery query) {
        return storeService.findPage(query);
    }

    @Override
    public PageBean<StoreDTO> listStorePage(StoreQuery query) {
        List<StoreDTO> list = storeService.findPage(query);
        return new PageBean<>(list);
    }

    @Override
    public StoreDTO detail(@PathVariable(value = "id") Long pk) {
        return storeService.detail(pk);
    }

    @Override
    public Boolean update(@PathVariable(value = "id") Long id, @RequestBody StoreDTO dto) {
        dto.setId(id);
        return storeService.update(dto);
    }

    @Override
    public Boolean updateBatch(@RequestBody List<StoreDTO> dtos) {
        return storeService.updateBatch(dtos);
    }

    @Override
    public Long create(@RequestBody StoreDTO dto) {
        return storeService.create(dto);
    }

    @Override
    public Boolean createBatch(List<StoreDTO> dtos) {
        return storeService.createBatch(dtos);
    }

    @Override
    public Boolean delete(@RequestBody List<Long> ids) {
        return storeService.delete(ids);
    }

    @Override
    public boolean isCodeUnique(StoreDTO dto) {
        return storeService.isCodeUnique(dto);
    }
}