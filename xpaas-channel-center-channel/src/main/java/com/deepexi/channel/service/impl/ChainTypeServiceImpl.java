package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.channel.dao.IChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.IChainTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChainTypeServiceImpl implements IChainTypeService {

    private AppRuntimeEnv appRuntimeEnv = AppRuntimeEnv.getInstance();
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
        query.setTenantId(appRuntimeEnv.getTenantId());
        query.setAppId(appRuntimeEnv.getAppId());
        //获取连锁列表
        List<ChainTypeDTO> chainTypeDTOS = getChainTypeList(query);
        // 得到所有连锁类型id
        Set<Long> idList = chainTypeDTOS.stream().map(ChainTypeDTO::getId).collect(Collectors.toSet());
        List<ChainTypeDO> parentChainTypeDOS = iChainTypeDAO.selectListByIds(idList);
        // id->连锁类型的map关系
        Map<Long, List<ChainTypeDO>> parentCollect =
                parentChainTypeDOS.stream().collect(Collectors.groupingBy(ChainTypeDO::getId));
        chainTypeDTOS.forEach(m -> {
            // 根据id对应设置attachmentPath字段
            List<ChainTypeDO> dos = parentCollect.get(m.getId());
            if (CollectionUtil.isEmpty(dos)) {
                m.setParentName("");
            } else {
                ChainTypeDO brandAttachmentDO = dos.get(0);
                m.setParentName(brandAttachmentDO.getChainTypeName() == null ? "" :
                        brandAttachmentDO.getChainTypeName());
            }
        });
        return chainTypeDTOS;
    }

    @Override
    public List<ChainTypeDTO> listChainType() {
        ChainTypeQuery query = new ChainTypeQuery();
        return getChainTypeList(query);
    }

    /**
     * 连锁列表单表查询
     * @param query
     * @return
     */
    public List<ChainTypeDTO> getChainTypeList(ChainTypeQuery query){
        query.setTenantId(appRuntimeEnv.getTenantId());
        query.setAppId(appRuntimeEnv.getAppId());
        List<ChainTypeDO> result =  iChainTypeDAO.listChainTypePage(query);
        List<ChainTypeDTO> chainTypeDTOS = ObjectCloneUtils.convertList(result, ChainTypeDTO.class, CloneDirection.OPPOSITE);
        return chainTypeDTOS;
    }


    @Override
    public Boolean insert(ChainTypeDTO dto) {
        dto.setTenantId(appRuntimeEnv.getTenantId());
        dto.setAppId(appRuntimeEnv.getTenantId());
        dto.setCreatedTime(new Date());
        dto.setCreatedBy("mumu");
        dto.setUpdatedTime(new Date());
        dto.setUpdatedBy("mumu");
        List<ChainTypeDO> list = iChainTypeDAO.list(new QueryWrapper<ChainTypeDO>().lambda().eq(ChainTypeDO::getChainTypeCode, dto.getChainTypeCode()));
        if(CollectionUtil.isNotEmpty(list)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        ChainTypeDO chainTypeDO = dto.clone(ChainTypeDO.class);
        return iChainTypeDAO.save(chainTypeDO);
    }

    @Override
    public Boolean update(ChainTypeDTO dto) {
        dto.setTenantId(appRuntimeEnv.getTenantId());
        dto.setAppId(appRuntimeEnv.getTenantId());
        dto.setUpdatedTime(new Date());
        dto.setUpdatedBy("mumu");
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
        //判断是否有下级分类
        if(iChainTypeDAO.haveChildren(ids)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //判断是否有被连锁引用
        //TODO

        return iChainTypeDAO.removeByIds(ids);
    }

}
