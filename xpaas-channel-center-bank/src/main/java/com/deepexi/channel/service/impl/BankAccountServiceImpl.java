package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.BankAccountDAO;
import com.deepexi.channel.domain.BankAccountDO;
import com.deepexi.channel.domain.BankAccountDTO;
import com.deepexi.channel.domain.BankAccountQuery;
import com.deepexi.channel.service.BankAccountService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<BankAccountDTO> findList(BankAccountQuery query) {
        if(query.getPage()!=null && query.getPage()!=-1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<BankAccountDO> bankAccountDOS = bankAccountDAO.findList(query);
        if(bankAccountDOS == null){
            return null;
        }
        return ObjectCloneUtils.convertList(bankAccountDOS, BankAccountDTO.class);
    }

//    @Override
//    public List<BankAccountDTO> getBankAccountByIds(List<Long> bankAccountIds) {
//        List<BankAccountDO> bankAccountDOS = bankAccountDAO.getBankAccountByIds(bankAccountIds);
//        if(bankAccountDOS == null){
//            return null;
//        }
//        return ObjectCloneUtils.convertList(bankAccountDOS, BankAccountDTO.class, CloneDirection.OPPOSITE);
//    }
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
    @Override
    public Boolean delete(List<Long> ids) {
        return bankAccountDAO.removeByIds(ids);
    }

    @Override
    public Long create(BankAccountDTO dto) {

        BankAccountDO eo=dto.clone(BankAccountDO.class,CloneDirection.FORWARD);

        bankAccountDAO.save(eo);

        return eo.getId();

    }

    @Override
    public Boolean update(BankAccountDTO dto) {

        if(null==dto){
            return false;
        }

        BankAccountDO eo=dto.clone(BankAccountDO.class,CloneDirection.FORWARD);

        return bankAccountDAO.updateById(eo);
    }

    @Override
    public Boolean deleteBankAccounts(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return false;
        }

        return bankAccountDAO.removeByIds(ids);

    }

}