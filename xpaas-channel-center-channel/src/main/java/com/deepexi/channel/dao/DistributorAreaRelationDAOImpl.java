package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDO;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDO;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDO;
import com.deepexi.channel.mapper.AreaMapper;
import com.deepexi.channel.mapper.DistributorAreaRelationMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorAreaRelationDAOImpl extends ServiceImpl<DistributorAreaRelationMapper, DistributorAreaRelationDO> implements DistributorAreaRelationDAO {

    @Override
    public int deleteByDistributorId(long distributorId) {

        UpdateWrapper<DistributorAreaRelationDO> wp=new UpdateWrapper<>();

        wp.eq("distributor_id",distributorId);

        return baseMapper.delete(wp);
    }

    @Override
    public int deleteBatchByDistributorIds(List<Long> distributorIdList) {

        UpdateWrapper<DistributorAreaRelationDO> wp=new UpdateWrapper<>();

        wp.in("distributor_id",distributorIdList);

        return baseMapper.delete(wp);

    }

    @Override
    public boolean updateById(DistributorAreaRelationDO eo){

        return updateById(eo);
    }

    @Override
    public boolean updateBatchById(List<DistributorAreaRelationDO> eoList) {

        return updateBatchById(eoList);
    }

    @Override
    public List<DistributorAreaRelationDO> findAllByDistributorId(Long distributorId) {

        QueryWrapper<DistributorAreaRelationDO> wp=new QueryWrapper<>();

        wp.eq("distributor_id",distributorId);

        return baseMapper.selectList(wp);
    }

    @Override
    public DistributorAreaRelationDO getOne(Long distributorId) {

        QueryWrapper<DistributorAreaRelationDO> wp=new QueryWrapper<>();

        wp.eq("distributor_id",distributorId);

        return baseMapper.selectOne(wp);
    }

    @Override
    public List<DistributorAreaRelationDO> findAllByAreaIds(List<Long> areaIdList) {

        QueryWrapper<DistributorAreaRelationDO> wp=new QueryWrapper<>();

        wp.in("area_id",areaIdList);

        return baseMapper.selectList(wp);
    }

    @Override
    public int deleteBatchByAreaIds(List<Long> areaIdList) {

        QueryWrapper wp=new QueryWrapper();

        List<DistributorAreaRelationDO> eoList = findAllByAreaIds(areaIdList);

        List<Long> idList=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(eoList)){
            idList=eoList.stream().map(DistributorAreaRelationDO::getId).collect(Collectors.toList());
        }

        if(CollectionUtils.isNotEmpty(idList)){

           return baseMapper.deleteBatchIds(idList);
        }

        return 0;
    }



}
