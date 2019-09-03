package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreGradeRelationDAO;
import com.deepexi.channel.domain.store.StoreGradeDO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.channel.domain.store.StoreGradeRelationDO;
import com.deepexi.channel.mapper.StoreGradeMapper;
import com.deepexi.channel.mapper.StoreGradeRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreGradeRelationDAOImpl extends ServiceImpl<StoreGradeRelationMapper, StoreGradeRelationDO> implements StoreGradeRelationDAO {
    @Autowired
    StoreGradeRelationMapper storeGradeRelationMapper;
}
