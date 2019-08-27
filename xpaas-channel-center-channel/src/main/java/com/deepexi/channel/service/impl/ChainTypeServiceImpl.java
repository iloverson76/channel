package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.IChainTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChainTypeServiceImpl implements IChainTypeService {

//    private AppRuntimeEnv appRuntimeEnv = AppRuntimeEnv.getInstance();
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
    public List<ChainTypeDTO> listChainType(ChainTypeQuery query) {
//        query.setTenantId(appRuntimeEnv.getTenantId());
//        query.setAppId(appRuntimeEnv.getAppId());
        List<ChainTypeDO> result =  iChainTypeDAO.listChainTypePage(query);
        return ObjectCloneUtils.convertList(result, ChainTypeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean insert(ChainTypeDTO dto) {
        ChainTypeDO chainTypeDO = dto.clone(ChainTypeDO.class);
        return iChainTypeDAO.save(chainTypeDO);
    }

    @Override
    public Boolean update(ChainTypeDTO dto) {
        if (dto.getId() == null || dto.getId() == 0L) {
            return false;
        }
        boolean result = iChainTypeDAO.updateById(dto.clone(ChainTypeDO.class));
        return result;
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return iChainTypeDAO.removeByIds(ids);
    }
}
