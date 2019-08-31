package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.BankAccountDAO;
import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcBankAccount;
import com.deepexi.channel.service.BankAccountService;
import com.deepexi.channel.mapper.BankAccountMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Override
    public List<BankAccountDTO>  saveBatch(List<BankAccountDTO> bankAccountDTOS) {
        List<BankAccountDO> bankAccountDOS = ObjectCloneUtils.convertList(bankAccountDTOS, BankAccountDO.class);
//        return
        bankAccountDAO.saveBatch(bankAccountDOS);
        List<BankAccountDTO> bankAccountDTOS1 = ObjectCloneUtils.convertList(bankAccountDOS, BankAccountDTO.class, CloneDirection.OPPOSITE);
        return bankAccountDTOS1;
    }

    @Override
    public List<BankAccountDTO> getBankAccountByIds(List<Long> bankAccountIds) {
        List<BankAccountDO> bankAccountDOS = bankAccountDAO.getBankAccountByIds(bankAccountIds);
        if(bankAccountDOS == null){
            return null;
        }
        return ObjectCloneUtils.convertList(bankAccountDOS, BankAccountDTO.class, CloneDirection.OPPOSITE);
    }
//
//    @Override
//    public PageBean<CcBankAccount> findPage(CcBankAccount eo, Integer page, Integer size) {
//        PageHelper.startPage(page, size);
//        List<CcBankAccount> pages =  bankAccountMapper.findList(eo);
//        return new PageBean<CcBankAccount>(pages);
//    }
//
//    @Override
//    public List<CcBankAccount> findAll(CcBankAccount eo) {
//        List<CcBankAccount> list = bankAccountMapper.findList(eo);
//        return list;
//    }
//    @Override
//    public CcBankAccount detail(Integer  pk) {
//        CcBankAccount eo = bankAccountMapper.selectById(pk);
//        return eo;
//    }
//
//    @Override
//    public Boolean update(Integer  id,CcBankAccount eo) {
//        CcBankAccount old = bankAccountMapper.selectById(id);
//        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
//        int result = bankAccountMapper.updateById(old);
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean create(CcBankAccount eo) {
//        int result = bankAccountMapper.insert(eo);
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean delete(Integer  pk) {
//        int result = bankAccountMapper.deleteBatchIds(Arrays.asList(pk));
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean delete(Integer ...pks) {
//        int result = bankAccountMapper.deleteBatchIds(Arrays.asList(pks));
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }

}