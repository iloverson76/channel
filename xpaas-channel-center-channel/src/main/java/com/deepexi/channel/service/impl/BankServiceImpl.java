package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.BankDAO;
import com.deepexi.channel.domain.bank.BankDO;
import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.service.BankService;
import com.deepexi.channel.mapper.BankMapper;
import java.util.Arrays;import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BankDAO bankDAO;

//    @Override
//    public PageBean<CcBank> findPage(CcBank eo, Integer page, Integer size) {
//        PageHelper.startPage(page, size);
//        List<CcBank> pages =  bankMapper.findList(eo);
//        return new PageBean<CcBank>(pages);
//    }
//
//    @Override
//    public List<CcBank> findAll(CcBank eo) {
//        List<CcBank> list = bankMapper.findList(eo);
//        return list;
//    }
//    @Override
//    public CcBank detail(Integer  pk) {
//        CcBank eo = bankMapper.selectById(pk);
//        return eo;
//    }
//
//    @Override
//    public Boolean update(Integer  id,CcBank eo) {
//        CcBank old = bankMapper.selectById(id);
//        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
//        int result = bankMapper.updateById(old);
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
    @Override
    public Boolean create(BankDTO bankDTO) {
        boolean result = bankDAO.save(bankDTO.clone(BankDO.class));
        return result;
    }
//
//    @Override
//    public Boolean delete(Integer  pk) {
//        int result = bankMapper.deleteBatchIds(Arrays.asList(pk));
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean delete(Integer ...pks) {
//        int result = bankMapper.deleteBatchIds(Arrays.asList(pks));
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public List<BankDTO> listBank() {
        List<BankDO> bankDOS = bankDAO.findAll();
        List<BankDTO> bankDTOS = ObjectCloneUtils.convertList(bankDOS, BankDTO.class, CloneDirection.OPPOSITE);
        return bankDTOS;
    }

    @Override
    public List<BankDTO> getBankByIds(List<Long> bankIds) {
        List<BankDO> bankDOS = bankDAO.getBankByIds(bankIds);
        if(CollectionUtil.isEmpty(bankDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(bankDOS, BankDTO.class, CloneDirection.OPPOSITE);
    }

}