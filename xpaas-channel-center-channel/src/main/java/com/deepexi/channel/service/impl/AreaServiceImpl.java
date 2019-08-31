package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.AreaDAO;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcArea;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.mapper.AreaMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaServiceImpl implements AreaService{

    @Autowired
    AreaDAO areaDAO;

    @Transactional
    @Override
    public Long create(AreaDTO dto) {

        AreaDO ado=dto.clone(AreaDO.class, CloneDirection.FORWARD);

        areaDAO.save(ado);

        return ado.getId();
    }
}