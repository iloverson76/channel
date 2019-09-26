package com.deepexi.channel.api.impl.impl;

import com.deepexi.channel.api.impl.StoreGradeApi;
import com.deepexi.channel.domain.StoreGradeDTO;
import com.deepexi.channel.domain.StoreGradeQuery;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/25 20:14
 */
@Service
public class StoreGradeApiImpl implements StoreGradeApi {

    @Autowired
    StoreGradeService storeGradeService;

    @Override
    public List<StoreGradeDTO> listStoreGrade(StoreGradeQuery query) {
        return storeGradeService.findPage(query);
    }

    @Override
    public PageBean<StoreGradeDTO> listStoreGradePage(StoreGradeQuery query) {
        List<StoreGradeDTO> list = storeGradeService.findPage(query);
        return new PageBean<>(list);
    }

    @Override
    public StoreGradeDTO detail(@PathVariable(value = "id") Long pk) {
        return storeGradeService.detail(pk);
    }

    @Override
    public Boolean update(@PathVariable(value = "id") Long id, StoreGradeDTO dto) {
        dto.setId(id);
        return storeGradeService.update(dto);
    }

    @Override
    public Boolean updateBatch(@RequestBody List<StoreGradeDTO> dtos) {
        return storeGradeService.updateBatch(dtos);
    }

    @Override
    public Long create(@RequestBody StoreGradeDTO dto) {
        return storeGradeService.create(dto);
    }

    @Override
    public Boolean createBatch(@RequestBody List<StoreGradeDTO> dtos) {
        return storeGradeService.createBatch(dtos);
    }

    @Override
    public Boolean delete(@RequestBody List<Long> ids) {
        return storeGradeService.delete(ids);
    }

    @Override
    public boolean isCodeUnique(StoreGradeDTO dto) {
        return storeGradeService.isCodeUnique(dto);
    }

    @Override
    public boolean isNameUnique(StoreGradeDTO dto) {
        return storeGradeService.isNameUnique(dto);
    }
}