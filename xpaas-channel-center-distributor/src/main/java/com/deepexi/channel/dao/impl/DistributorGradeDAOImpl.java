package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.DistributorGradeDAO;
import com.deepexi.channel.domain.DistributorGradeDO;
import com.deepexi.channel.domain.DistributorGradeQuery;
import com.deepexi.channel.mapper.DistributorGradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorGradeDAOImpl extends ServiceImpl<DistributorGradeMapper, DistributorGradeDO> implements DistributorGradeDAO {

    @Autowired
    DistributorGradeMapper distributorGradeMapper;

    @Override
    public List<DistributorGradeDO> findPage(DistributorGradeQuery query) {
        return baseMapper.findPage(query);
    }

    @Override
    public List<DistributorGradeDO> listParentNodesForCreate(long systemId,String path) {
        return distributorGradeMapper.listParentNodesForCreate(systemId,path);
    }

    @Override
    public int getByCode(String garedCode) {

        QueryWrapper<DistributorGradeDO> wp=new QueryWrapper() ;

        wp.eq("distributor_grade_code",garedCode);

        return distributorGradeMapper.selectCount(wp);
    }

    @Override
    public List<DistributorGradeDO> findAllBySystem(Long systemId) {

        QueryWrapper<DistributorGradeDO> wp=new QueryWrapper() ;

        wp.eq("grade_system_id",systemId);

        return distributorGradeMapper.selectList(wp);
    }
}
