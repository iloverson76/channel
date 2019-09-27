package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.impl.StoreHistoryApi;
import com.deepexi.channel.domain.StoreHistoryDTO;
import com.deepexi.channel.domain.StoreHistoryQuery;
import com.deepexi.channel.service.StoreHistoryService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 10:23
 */
@Service
public class StoreHistoryApiImpl implements StoreHistoryApi {

    @Autowired
    private StoreHistoryService storeHistoryService;
    @Override
    public List<StoreHistoryDTO> listStoreHistory(StoreHistoryQuery query) {
        return storeHistoryService.findPage(query);
    }

    @Override
    public PageBean<StoreHistoryDTO> listStoreHistoryPage(StoreHistoryQuery query) {
        List<StoreHistoryDTO> list = storeHistoryService.findPage(query);
        return new PageBean<>(list);
    }

    @Override
    public StoreHistoryDTO detail(@PathVariable(value = "id") Long pk) {
        return storeHistoryService.detail(pk);
    }

    @Override
    public Boolean update(@PathVariable(value = "id") Long id, @RequestBody StoreHistoryDTO dto) {
        dto.setId(id);
        return storeHistoryService.update(dto);
    }

    @Override
    public Boolean updateBatch(@RequestBody List<StoreHistoryDTO> dtos) {
        return storeHistoryService.updateBatch(dtos);
    }

    @Override
    public Long create(@RequestBody StoreHistoryDTO dto) {
        return storeHistoryService.create(dto);
    }

    @Override
    public Boolean createBatch(@RequestBody List<StoreHistoryDTO> dtos) {
        return storeHistoryService.createBatch(dtos);
    }

    @Override
    public Boolean delete(@RequestBody List<Long> ids) {
        return storeHistoryService.delete(ids);
    }

    @Override
    public Integer getStoreHistoryCountByStoreId(@PathVariable(value = "id") Long id) {
        return storeHistoryService.getStoreHistoryCountByStoreId(id);
    }
}