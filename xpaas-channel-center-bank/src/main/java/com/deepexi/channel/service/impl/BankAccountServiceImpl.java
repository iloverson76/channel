package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.BankAccountDAO;
import com.deepexi.channel.domain.BankAccountDO;
import com.deepexi.channel.domain.BankAccountDTO;
import com.deepexi.channel.domain.BankAccountQuery;
import com.deepexi.channel.service.BankAccountService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Override
    public List<BankAccountDTO> saveBatch(List<BankAccountDTO> bankAccountDTOS) {
        if (CollectionUtil.isEmpty(bankAccountDTOS)) {
            return Collections.emptyList();
        }
        List<BankAccountDO> bankAccountDOS = ObjectCloneUtils.convertList(bankAccountDTOS, BankAccountDO.class);
        bankAccountDAO.saveBatch(bankAccountDOS);
        List<BankAccountDTO> bankAccountDTOS1 = ObjectCloneUtils.convertList(bankAccountDOS, BankAccountDTO.class, CloneDirection.OPPOSITE);
        return bankAccountDTOS1;
    }

    @Override
    public List<BankAccountDTO> findList(BankAccountQuery query) {
        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<BankAccountDO> bankAccountDOS = bankAccountDAO.findList(query);
        if (null == bankAccountDOS) {
            return null;
        }
        return ObjectCloneUtils.convertList(bankAccountDOS, BankAccountDTO.class);
    }

    //
    @Override
    public Boolean delete(List<Long> ids) {
        return bankAccountDAO.removeByIds(ids);
    }

    @Override
    public Long create(BankAccountDTO dto) {

        BankAccountDO eo = dto.clone(BankAccountDO.class, CloneDirection.FORWARD);

        bankAccountDAO.save(eo);

        return eo.getId();

    }

    @Override
    public Boolean update(BankAccountDTO dto) {

        if (null == dto) {
            return false;
        }

        BankAccountDO eo = dto.clone(BankAccountDO.class, CloneDirection.FORWARD);

        return bankAccountDAO.updateById(eo);
    }

    @Override
    public Boolean deleteBankAccounts(List<Long> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }

        return bankAccountDAO.removeByIds(ids);

    }

}