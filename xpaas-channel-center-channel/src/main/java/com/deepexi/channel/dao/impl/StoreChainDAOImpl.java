package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreChainDAO;
import com.deepexi.channel.domain.store.StoreChainDO;
import com.deepexi.channel.mapper.StoreChainMapper;
import org.springframework.stereotype.Service;

@Service
public class StoreChainDAOImpl extends ServiceImpl<StoreChainMapper, StoreChainDO> implements StoreChainDAO {
}
