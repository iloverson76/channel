package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.AreaTypeDAO;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.mapper.AreaTypeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class AreaTypeDAOImpl extends ServiceImpl<AreaTypeMapper, AreaTypeDO> implements AreaTypeDAO {

    @Override
    public List<AreaTypeDO> listAreaTypePage(AreaTypeQuery query) {

        return baseMapper.listAreaTypePage(query);
    }

    @Override
    public List<String> listAreaTypeCode(String tenantId, String appId) {
        return baseMapper.listAreaTypeCode(tenantId, appId);
    }

    @Override
    public AreaTypeDO getChildNode(String tenantId, String appId, Long id) {
        return baseMapper.getChildNode(tenantId,appId,id);
    }

    @Override
    public List<AreaTypeDO> listNotLimitedNode(String tenantId, String appId) {
        return baseMapper.listNotLimitedNode(tenantId,appId);
    }

    @Override
    public List<AreaTypeDO> listChildNodes(String tenantId, String appId,String idPath) {
        return baseMapper.listChildNodes(tenantId,appId,idPath);
    }

    @Override
    public List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList) {

        return baseMapper.listAreaTypeByIds(areaTyeIdList);
    }
}
