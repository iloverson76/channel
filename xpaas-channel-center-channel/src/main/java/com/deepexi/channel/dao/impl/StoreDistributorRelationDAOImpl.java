package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreDistributorRelationDAO;
import com.deepexi.channel.domain.store.StoreDistributorRelationDO;
import com.deepexi.channel.domain.store.StoreDistributorRelationQuery;
import com.deepexi.channel.mapper.StoreDistributorRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/7 14:25
 */
@Service
public class StoreDistributorRelationDAOImpl  extends ServiceImpl<StoreDistributorRelationMapper, StoreDistributorRelationDO> implements StoreDistributorRelationDAO {

    @Autowired
    StoreDistributorRelationMapper storeDistributorRelationMapper;

    @Override
    public List<StoreDistributorRelationDO> findList(StoreDistributorRelationQuery query) {
        return storeDistributorRelationMapper.findList(query);
    }
}