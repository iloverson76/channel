package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.BankDAO;
import com.deepexi.channel.domain.BankDO;
import com.deepexi.channel.domain.BankDTO;
import com.deepexi.channel.domain.BankQuery;
import com.deepexi.channel.service.BankService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
@Service
public class BankServiceImpl implements BankService {

    @Value("#{'${bank.init.bankName}'.split(',')}")
    List<String> bankNames;
    @Autowired
    private BankDAO bankDAO;

    @Override
    public Boolean create(BankDTO bankDTO) {
        if(null == bankDTO){
            return false ;
        }
        return bankDAO.save(bankDTO.clone(BankDO.class));
    }

    @Override
    public List<BankDTO> findList(BankQuery query) {
        List<BankDO> list = bankDAO.findList(query);
        if(CollectionUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return ObjectCloneUtils.convertList(list, BankDTO.class);
    }

    @Override
    public Boolean delete(List<Long> ids) {
        return bankDAO.removeByIds(ids);
    }

    @Override
    public Boolean delete(Long id) {
        return bankDAO.removeById(id);
    }

    @Override
    public Boolean update(BankDTO bankDTO) {
        if(null == bankDTO){
            return false;
        }
        return bankDAO.updateById(bankDTO.clone(BankDO.class));
    }

    /**
     * @MethodName: listBank
     * @Description: 查询所有的银行账户，如果该应用列表为空，则进行初始化
     * @Param: []
     * @Return: java.util.List<com.deepexi.channel.domain.BankDTO>
     * @Author: mumu
     * @Date: 2019/9/20
    **/
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