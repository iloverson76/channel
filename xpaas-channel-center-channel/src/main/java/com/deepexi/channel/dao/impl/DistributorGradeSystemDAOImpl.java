package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.IAreaTypeDAO;
import com.deepexi.channel.dao.IDistributorGradeSystemDAO;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemDO;
import com.deepexi.channel.mapper.AreaTypeMapper;
import com.deepexi.channel.mapper.DistributorGradeSystemMapper;
import org.springframework.stereotype.Repository;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorGradeSystemDAOImpl extends ServiceImpl<DistributorGradeSystemMapper, DistributorGradeSystemDO> implements IDistributorGradeSystemDAO {

}
