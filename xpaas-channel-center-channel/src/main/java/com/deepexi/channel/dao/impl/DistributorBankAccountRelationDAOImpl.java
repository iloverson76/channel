package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.DistributorBankAccountRelationDAO;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDO;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDO;
import com.deepexi.channel.mapper.DistributorBankAccountRelationMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorBankAccountRelationDAOImpl extends ServiceImpl<DistributorBankAccountRelationMapper, DistributorBankAccountRelationDO> implements DistributorBankAccountRelationDAO {

    @Override
    public int deleteByDistributorId(long distributorId) {

        UpdateWrapper<DistributorBankAccountRelationDO> wp=new UpdateWrapper<>();

        wp.eq("distributor_id",distributorId);

        return baseMapper.delete(wp);
    }

    @Override
    public boolean deleteBatchByDistributorIds(List<Long> distributorIdList) {

        UpdateWrapper<DistributorBankAccountRelationDO> wp=new UpdateWrapper<>();

        wp.in("distributor_id",distributorIdList);

        boolean result=false;

        int updatedRow=baseMapper.delete(wp);

        if(updatedRow>0){
            result =true;
        }
        return result;
    }

    @Override
    public int deleteBatchByDistributorId(Long distributorId) {

        UpdateWrapper<DistributorBankAccountRelationDO> wp=new UpdateWrapper<>();

        wp.eq("distributor_id",distributorId);

        return baseMapper.delete(wp);
    }

    @Override
    public boolean updateById(DistributorBankAccountRelationDO eo){

        return updateById(eo);
    }

    @Override
    public boolean updateBatchById(List<DistributorBankAccountRelationDO> eoList) {

        return updateBatchById(eoList);
    }

    @Override
    public List<DistributorBankAccountRelationDO> findAllByDistributorIds(List<Long> distributorIds) {

        QueryWrapper<DistributorBankAccountRelationDO> wp=new QueryWrapper<>();

        wp.in("distributor_id",distributorIds);

        return baseMapper.selectList(wp);
    }

    @Override
    public DistributorBankAccountRelationDO findOne(long distributorId, long bankAccountId) {

        QueryWrapper<DistributorBankAccountRelationDO> wp=new QueryWrapper<>();

        wp.eq("distributor_id",distributorId);

        wp.eq("bank_account_id",bankAccountId);

        return baseMapper.selectOne(wp);
    }

}
