package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.AreaDAO;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.mapper.AreaMapper;
import org.springframework.stereotype.Repository;

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
}
