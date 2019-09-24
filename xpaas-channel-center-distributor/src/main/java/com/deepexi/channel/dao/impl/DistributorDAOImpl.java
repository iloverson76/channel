package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.DistributorDAO;
import com.deepexi.channel.domain.DistributorDO;
import com.deepexi.channel.domain.DistributorQuery;
import com.deepexi.channel.mapper.DistributorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorDAOImpl extends ServiceImpl<DistributorMapper, DistributorDO> implements DistributorDAO {

    @Autowired
    DistributorMapper distributorMapper;

    @Override
    public List<DistributorDO> findPage(DistributorQuery query) {

        return baseMapper.findPage(query);
    }

    @Override
    public List<String> listDistributorCode() {
        return distributorMapper.listDistributorCode();
    }

    @Override
    public List<String> listDistributorName() {
        return distributorMapper.listDistributorName();
    }

    @Override
    public List<String> listDistributorNameEn() {
        return distributorMapper.listDistributorNameEn();
    }
}
