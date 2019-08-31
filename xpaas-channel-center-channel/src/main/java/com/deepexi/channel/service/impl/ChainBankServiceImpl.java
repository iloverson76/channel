package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.ChainBankDAO;
import com.deepexi.channel.domain.bank.ChainBankDO;
import com.deepexi.channel.domain.bank.ChainBankDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcChainBank;
import com.deepexi.channel.service.ChainBankService;
import com.deepexi.channel.mapper.ChainBankMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class ChainBankServiceImpl implements ChainBankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChainBankDAO chainBankDAO;

    @Override
    public boolean saveBatch(List<ChainBankDTO> chainBankDTOS) {
        List<ChainBankDO> list = ObjectCloneUtils.convertList(chainBankDTOS,ChainBankDO.class);
        return chainBankDAO.saveBatch(list);
    }

    @Override
    public List<ChainBankDTO> getChainBankByChainId(Long id) {
        List<ChainBankDO> chainBankDOS = chainBankDAO.getChainBankByChainId(id);
        if(CollectionUtil.isEmpty(chainBankDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(chainBankDOS, ChainBankDTO.class, CloneDirection.OPPOSITE);
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