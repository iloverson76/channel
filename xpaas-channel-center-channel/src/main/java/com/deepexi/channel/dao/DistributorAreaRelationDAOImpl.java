package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDO;
import com.deepexi.channel.mapper.AreaMapper;
import com.deepexi.channel.mapper.DistributorAreaRelationMapper;
import org.springframework.stereotype.Repository;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorAreaRelationDAOImpl extends ServiceImpl<DistributorAreaRelationMapper, DistributorAreaRelationDO> implements DistributorAreaRelationDAO {

}
