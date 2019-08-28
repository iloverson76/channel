package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.ChainDAO;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.mapper.ChainMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ChainDAOImpl extends ServiceImpl<ChainMapper, ChainDO> implements ChainDAO {
}
