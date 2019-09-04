package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.AreaDAO;
import com.deepexi.channel.dao.DistributorDAO;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.distributor.DistributorDO;
import com.deepexi.channel.mapper.AreaMapper;
import com.deepexi.channel.mapper.DistributorMapper;
import org.springframework.stereotype.Repository;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorDAOImpl extends ServiceImpl<DistributorMapper, DistributorDO> implements DistributorDAO {


}
