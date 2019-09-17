package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.AreaDAO;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.mapper.AreaMapper;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class AreaDAOImpl extends ServiceImpl<AreaMapper, AreaDO> implements AreaDAO {

    @Override
    public List<AreaDO> listAreaPage(AreaQuery query) {

        return baseMapper.listAreaPage(query);
    }

    @Override
    public List<AreaDO> listChildrenAreas(Long areaId) {

        QueryWrapper<AreaDO> wp=new QueryWrapper<>();

        wp.like("path","/"+areaId+"/");

        return baseMapper.selectList(wp);
    }

    @Override
    public List<AreaDO> listLinkedAreasByType(Long areaTypeId) {

        QueryWrapper<AreaDO> wp=new QueryWrapper<>();

        wp.eq("area_type_id",areaTypeId);

        return baseMapper.selectList(wp);
    }

    @Override
    public List<AreaDO> findAllByIds(List<Long> ids) {

        QueryWrapper<AreaDO> wp=new QueryWrapper<>();

        wp.in("id",ids);

        return baseMapper.selectList(wp);
    }

    @Override
    public List<AreaDO> findTree() {

        QueryWrapper<AreaDO> wp=new QueryWrapper<>();

        String reg="^/([0-9])*$|^/([0-9])*/([0-9])*$|^/([0-9])*/([0-9])*/([0-9])*$";

        wp.likeRight("path",reg);

        return baseMapper.selectList(wp);
    }
}
