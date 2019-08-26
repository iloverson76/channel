package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.IChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.mapper.ChainTypeMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ChainTypeDAOImpl extends ServiceImpl<ChainTypeMapper, ChainTypeDO> implements IChainTypeDAO {
}
