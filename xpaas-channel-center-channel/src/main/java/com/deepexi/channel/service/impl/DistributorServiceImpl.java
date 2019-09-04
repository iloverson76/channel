package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.distributor.DistributorDTO;
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
    @Override
    public boolean create(DistributorDTO dto) {


        return false;
    }


}