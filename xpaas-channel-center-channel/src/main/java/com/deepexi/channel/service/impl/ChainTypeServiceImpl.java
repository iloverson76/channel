package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.service.IChainTypeService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChainTypeServiceImpl implements IChainTypeService {
    @Autowired
    IChainTypeDAO iChainTypeDAO;

    @Override
    public ChainTypeDTO getChainType(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        ChainTypeDO category = iChainTypeDAO.getById(id);
        if (null == category) {
            return null;
        }
        return category.clone(ChainTypeDTO.class);
    }

    @Override
    public List<ChainTypeDTO> listChainType(ChainTypeQuery query, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Page<ChainTypeDO> result =  iChainTypeDAO.listChainTypePage(query);
        return ObjectCloneUtils.convertList(result, ChainTypeDTO.class, CloneDirection.OPPOSITE);
    }
}
