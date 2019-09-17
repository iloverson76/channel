package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.BankDAO;
import com.deepexi.channel.domain.bank.BankDO;
import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.service.BankService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("#{'${bank.init.bankName}'.split(',')}")
    List<String> bankNames;
    @Autowired
    private BankDAO bankDAO;

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
        if(CollectionUtil.isEmpty(bankDOS)){
            //银行列表为空，说明该appid下的银行列表为空，触发初始化
            this.initBankList();
            //初始化完再查询一次
            bankDOS = bankDAO.findAll();
        }

        List<BankDTO> bankDTOS = ObjectCloneUtils.convertList(bankDOS, BankDTO.class, CloneDirection.OPPOSITE);
        return bankDTOS;
    }

    /**
     * @MethodName: initBankList
     * @Description: 初始化银行列表，每个新的应用都会初始化一个列表
     * @Param: []
     * @Return: void
     * @Author: mumu
     * @Date: 2019/9/17
    **/
    private void initBankList() {
        List<BankDTO> bankDTOS = new LinkedList<>();
        bankNames.forEach(b->{
            BankDTO bankDTO = BankDTO.builder().bankName(b).build();
            bankDTOS.add(bankDTO);
        });
        this.createBatch(bankDTOS);
    }

    @Override
    public List<BankDTO> getBankByIds(List<Long> bankIds) {
        List<BankDO> bankDOS = bankDAO.getBankByIds(bankIds);
        if(CollectionUtil.isEmpty(bankDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(bankDOS, BankDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean createBatch(List<BankDTO> bankDTOS) {
        if(CollectionUtil.isEmpty(bankDTOS)){
            return false;
        }
        List<BankDO> list = ObjectCloneUtils.convertList(bankDTOS, BankDO.class);
        return bankDAO.saveBatch(list);
    }

}