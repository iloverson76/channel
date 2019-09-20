package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.ChainBankDAO;
import com.deepexi.channel.domain.bank.ChainBankDO;
import com.deepexi.channel.domain.bank.ChainBankDTO;
import com.deepexi.channel.domain.bank.ChainBankQuery;
import com.deepexi.channel.service.ChainBankService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChainBankServiceImpl implements ChainBankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChainBankDAO chainBankDAO;

    @Override
    public List<ChainBankDTO> findList(ChainBankQuery query) {
        if (query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<ChainBankDO> list = chainBankDAO.findList(query);
        if(CollectionUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return ObjectCloneUtils.convertList(list, ChainBankDTO.class);
    }

    @Override
    public boolean saveBatch(List<ChainBankDTO> chainBankDTOS) {
        List<ChainBankDO> list = ObjectCloneUtils.convertList(chainBankDTOS,ChainBankDO.class);
        return chainBankDAO.saveBatch(list);
    }

//    @Override
//    public List<ChainBankDTO> getChainBankByChainId(Long id) {
//        List<ChainBankDO> chainBankDOS = chainBankDAO.getChainBankByChainId(id);
//        if(CollectionUtil.isEmpty(chainBankDOS)){
//            return null;
//        }
//        return ObjectCloneUtils.convertList(chainBankDOS, ChainBankDTO.class, CloneDirection.OPPOSITE);
//    }

    @Override
    public boolean deleteByChainId(Long id) {
        return chainBankDAO.deleteByChainId(id);
    }

//    @Override
//    public PageBean<CcChainBank> findPage(CcChainBank eo, Integer page, Integer size) {
//        PageHelper.startPage(page, size);
//        List<CcChainBank> pages =  ccChainBankMapper.findList(eo);
//        return new PageBean<CcChainBank>(pages);
//    }
//
//    @Override
//    public List<CcChainBank> findAll(CcChainBank eo) {
//        List<CcChainBank> list = ccChainBankMapper.findList(eo);
//        return list;
//    }
//    @Override
//    public CcChainBank detail(Integer  pk) {
//        CcChainBank eo = ccChainBankMapper.selectById(pk);
//        return eo;
//    }
//
//    @Override
//    public Boolean update(Integer  id,CcChainBank eo) {
//        CcChainBank old = ccChainBankMapper.selectById(id);
//        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
//        int result = ccChainBankMapper.updateById(old);
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean create(CcChainBank eo) {
//        int result = ccChainBankMapper.insert(eo);
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean delete(Integer  pk) {
//        int result = ccChainBankMapper.deleteBatchIds(Arrays.asList(pk));
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean delete(Integer ...pks) {
//        int result = ccChainBankMapper.deleteBatchIds(Arrays.asList(pks));
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }

}