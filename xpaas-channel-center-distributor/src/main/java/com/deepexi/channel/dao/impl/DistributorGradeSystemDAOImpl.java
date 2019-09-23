package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.DistributorGradeSystemDAO;
import com.deepexi.channel.domain.DistributorGradeSystemDO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;
import com.deepexi.channel.mapper.DistributorGradeSystemMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorGradeSystemDAOImpl extends ServiceImpl<DistributorGradeSystemMapper, DistributorGradeSystemDO> implements DistributorGradeSystemDAO {

    @Override
    public List<DistributorGradeSystemDO> findPage(DistributorGradeSystemQuery query) {

        return baseMapper.findPage(query);
    }
}
