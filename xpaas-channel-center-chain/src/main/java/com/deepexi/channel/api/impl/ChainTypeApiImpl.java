package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.ChainTypeApi;
import com.deepexi.channel.domain.ChainTypeDTO;
import com.deepexi.channel.domain.ChainTypeQuery;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 19:35
 */
@Service
public class ChainTypeApiImpl implements ChainTypeApi {

    @Autowired
    ChainTypeService chainTypeService;

    @Override
    public List<ChainTypeDTO> findList(ChainTypeQuery query) {
        return chainTypeService.findPage(query);
    }

    @Override
    public PageBean<ChainTypeDTO> findListPage(ChainTypeQuery query) {
        List<ChainTypeDTO> list = chainTypeService.findPage(query);
        return new PageBean<>(list);
    }

    @Override
    public ChainTypeDTO detail(@PathVariable(value = "id") Long id) {
        return chainTypeService.detail(id);
    }

    @Override
    public Boolean update(@PathVariable(value = "id") Long id, @RequestBody ChainTypeDTO dto) {
        dto.setId(id);
        return chainTypeService.update(dto);
    }

    @Override
    public Boolean updateBatch(@RequestBody List<ChainTypeDTO> list) {
        return chainTypeService.updateBatch(list);
    }

    @Override
    public Long create(@RequestBody ChainTypeDTO dto) {
        return chainTypeService.create(dto);
    }

    @Override
    public Boolean createBatch(@RequestBody List<ChainTypeDTO> dtos) {
        return chainTypeService.createBatch(dtos);
    }

    @Override
    public Boolean delete(@RequestBody List<Long> ids) {
        return chainTypeService.delete(ids);
    }

    @Override
    public boolean isCodeUnique(ChainTypeDTO dto) {
        return chainTypeService.isCodeUnique(dto);
    }

    @Override
    public boolean isNameUnique(ChainTypeDTO dto) {
        return chainTypeService.isNameUnique(dto);
    }

    @Override
    public boolean isParentLegal(ChainTypeDTO dto) {
        return chainTypeService.isParentLegal(dto);
    }

    @Override
    public List<ChainTypeDTO> listParentNodesForCreate() {
        return chainTypeService.listParentNodesForCreate();
    }

    @Override
    public List<ChainTypeDTO> listParentNodesForUpdate(@PathVariable(value = "id") Long id) {
        return chainTypeService.listParentNodesForUpdate(id);
    }
}