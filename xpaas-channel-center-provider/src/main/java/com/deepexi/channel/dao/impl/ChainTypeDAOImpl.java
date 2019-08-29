package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.ChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.domain.eo.CcChainType;
import com.deepexi.channel.mapper.ChainTypeMapper;
import com.deepexi.util.CollectionUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ChainTypeDAOImpl extends ServiceImpl<ChainTypeMapper, CcChainType> implements ChainTypeDAO {

//    @Autowired
//    ChainTypeMapper chainTypeMapper;
//
//    @Override
//    public List<ChainTypeDO> listChainTypePage(ChainTypeQuery query) {
//        if (query.getPage() != null && query.getPage() != -1) {
//            PageHelper.startPage(query.getPage(), query.getSize());
//        }
//        return chainTypeMapper.listChainTypePage(query);
//    }
//
//    @Override
//    public List<ChainTypeDO> selectListByIds(Collection<Long> idList) {
//        return chainTypeMapper.selectList(new QueryWrapper<ChainTypeDO>().lambda().in(ChainTypeDO::getId, idList));
//    }
//
//    @Override
//    public boolean haveChildren(List<Long> ids) {
//        //获得所有子节点
//        Collection<ChainTypeDO> chainTypeDOS = this.listByIds(ids);
//        //没有子节点
//        if(CollectionUtil.isEmpty(chainTypeDOS)){
//            return true;
//        }
//        //判断子节点是否也被删除，如果子节点不被删除，则拒绝删除
//        for(ChainTypeDO a : chainTypeDOS){
//            if(!ids.contains(a.getId())){
//                return false;
//            }
//        }
//        //父节点跟子节点一同删除
//        return true;
//    }
}
