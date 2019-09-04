package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorDAO;
import com.deepexi.channel.domain.distributor.DistributorDO;
import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributor;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.channel.mapper.DistributorMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class DistributorServiceImpl implements DistributorService {

    @Autowired
    DistributorService distributorService;

    @Autowired
    DistributorDAO distributorDAO;

    @Override
    public long create(DistributorDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorDO eo=dto.clone(DistributorDO.class, CloneDirection.FORWARD);

        distributorDAO.save(eo);

        return eo.getId();
    }



}