package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.ChainApi;
import com.deepexi.channel.domain.ChainDTO;
import com.deepexi.channel.domain.ChainDetailDTO;
import com.deepexi.channel.domain.ChainQuery;
import com.deepexi.channel.service.ChainService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 20:18
 */
@Service
public class ChainApiImpl implements ChainApi {

    @Autowired
    ChainService chainService;

    @Override
    public List<ChainDTO> findList(ChainQuery query) {
        return chainService.findPage(query);
    }

    @Override
    public PageBean<ChainDTO> findPage(ChainQuery query) {
        List<ChainDTO> list = chainService.findPage(query);
        return new PageBean<>(list);
    }

    @Override
    public ChainDTO detail(@PathVariable(value = "id") Long id) {
        return chainService.detail(id);
    }

    @Override
    public Boolean update(@PathVariable(value = "id") Long id, @RequestBody ChainDTO dto) {
        dto.setId(id);
        return chainService.update(dto);
    }

    @Override
    public Boolean updateBatch(@RequestBody List<ChainDTO> dtos) {
        return chainService.updateBatch(dtos);
    }

    @Override
    public Long create(@RequestBody ChainDTO dto) {
        return chainService.create(dto);
    }

    @Override
    public Boolean createBatch(@RequestBody List<ChainDTO> dtos) {
        return chainService.createBatch(dtos);
    }

    @Override
    public Boolean delete(@RequestBody List<Long> ids) {
        return chainService.delete(ids);
    }

    @Override
    public boolean isCodeUnique(ChainDTO dto) {
        return chainService.isCodeUnique(dto);
    }

    @Override
    public boolean updatePathAndParentIdBatch(@RequestBody List<ChainDTO> chainDTOS) {
        return chainService.updatePathAndParentIdBatch(chainDTOS);
    }

    @Override
    public List<ChainDTO> getChainTreeNode() {
        return chainService.getChainTreeNode();
    }

    @Override
    public Boolean resetTree() {
        return chainService.resetTree();
    }

    @Override
    public boolean isNameUnique(ChainDetailDTO dto) {
        return chainService.isNameUnique(dto);
    }

    @Override
    public Boolean updatePathAndParentId(@RequestBody ChainDTO chainDTO) {
        return chainService.updatePathAndParentId(chainDTO);
    }
}