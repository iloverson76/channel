package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.AreaDAO;
import com.deepexi.channel.dao.DistributorDAO;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.distributor.DistributorDO;
import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.channel.domain.distributor.DistributorQuery;
import com.deepexi.channel.mapper.AreaMapper;
import com.deepexi.channel.mapper.DistributorMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorDAOImpl extends ServiceImpl<DistributorMapper, DistributorDO> implements DistributorDAO {


    @Override
    public List<DistributorDO> findPage(DistributorQuery query) {

        return baseMapper.findPage(query);
    }
}
