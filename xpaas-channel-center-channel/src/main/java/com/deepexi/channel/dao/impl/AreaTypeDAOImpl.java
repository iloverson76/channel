package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.AreaTypeDAO;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.mapper.AreaTypeMapper;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.apache.commons.collections.CollectionUtils;
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
    public List<AreaTypeDO> listChildNodes(String idPath) {
        return baseMapper.listChildNodes(idPath);
    }

    @Override
    public List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList) {

        List<AreaTypeDO> eoList= baseMapper.listAreaTypeByIds(areaTyeIdList);

        return ObjectCloneUtils.convertList(eoList,AreaTypeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public List<AreaTypeDO> listLinkedAreas(long pk) {

        return baseMapper.listLinkedAreas(pk);
    }

    @Override
    public List<AreaTypeDO> findByAreaIdNotInLinkIdAll(List<Long> linkIdList) {
        return baseMapper.selectList(new QueryWrapper<AreaTypeDO>().lambda().notIn(AreaTypeDO::getLinkId, linkIdList));
    }

    @Override
    public boolean update(AreaTypeDO tdo) {

        UpdateWrapper<AreaTypeDO> wrapper = new UpdateWrapper<>();

        wrapper.eq("id", tdo.getId());

        int row = baseMapper.updateById(tdo);

        boolean result=false;

        if(row>0){
            result =true;
        }

        return result;
    }
}
