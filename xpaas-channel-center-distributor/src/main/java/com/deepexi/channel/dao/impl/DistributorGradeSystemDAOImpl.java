package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.DistributorGradeSystemDAO;
import com.deepexi.channel.domain.DistributorGradeSystemDO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;
import com.deepexi.channel.mapper.DistributorGradeSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorGradeSystemDAOImpl extends ServiceImpl<DistributorGradeSystemMapper, DistributorGradeSystemDO> implements DistributorGradeSystemDAO {

    @Autowired
    DistributorGradeSystemMapper distributorGradeSystemMapper;

    @Override
    public List<DistributorGradeSystemDO> findPage(DistributorGradeSystemQuery query) {

        return baseMapper.findPage(query);
    }

    @Override
    public List<String> listGradeSystemCode() {
        return distributorGradeSystemMapper.listGradeSystemCode();
    }

    @Override
    public List<String> listGradeSystemName() {
        return distributorGradeSystemMapper.listGradeSystemName();
    }

    @Override
    public List<String> listGradeSystemNameEn() {
        return distributorGradeSystemMapper.listGradeSystemNameEn();
    }

}
