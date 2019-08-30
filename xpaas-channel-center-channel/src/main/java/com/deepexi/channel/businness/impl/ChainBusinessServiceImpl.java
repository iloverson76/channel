package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.ChainBusinessService;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.ChainBankDTO;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChainBusinessServiceImpl implements ChainBusinessService {

    @Autowired
    ChainService chainService;
    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    ChainBankService chainBankService;
    @Autowired
    ChainTypeService chainTypeService;

    @Override
    @Transactional
    public Long insertChain(ChainDTO dto) {
        //校验编码是否重复
        if(!chainService.isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //新增连锁基本信息
        Long result = chainService.create(dto);
        //批量新增连锁账户信息
        List<BankAccountDTO> bankAccountDTOS = dto.getBankAccountList();
        if(CollectionUtil.isEmpty(bankAccountDTOS)){
            return result;
        }
        boolean insertBankAccountResult = bankAccountService.saveBatch(bankAccountDTOS);

        //批量新增账户、连锁关联信息
        List<ChainBankDTO> chainBankDTOS = new ArrayList<>();
        for(BankAccountDTO bankAccount:bankAccountDTOS){
            ChainBankDTO chainBankDO = ChainBankDTO.builder()
                    .bankAccountId(bankAccount.getId())
                    .chainId(dto.getId())
                    .build();
            chainBankDO.setVersion(dto.getVersion());

            chainBankDTOS.add(chainBankDO);
        }
        boolean insertChainBankResult = chainBankService.saveBatch(chainBankDTOS);
        return result;
    }

    @Override
    public ChainDTO getChain(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        //查询分类
        ChainDTO dto = chainService.detail(id);
        if(dto == null){
            return null;
        }
        //查询连锁所属分类, 获取分类信息
        ChainTypeDTO chainTypeDTO = chainTypeService.detail(dto.getChainTypeId());
        if(chainTypeDTO != null){
            dto.setChainTypeName(chainTypeDTO.getChainTypeName());
        }

        //查询连锁的所有账户，获取账户信息
        //TODO


        return dto;
    }

    @Override
    public Boolean deleteChain(List<Long> ids) {
        //TODO 判断连锁删除是否合法,是否具有子节点

        //TODO 删除的连锁是否被其他门店所关联

        //删除合法
        return chainService.delete(ids);
    }

    @Override
    @Transactional
    public Boolean updateChain(ChainDTO dto) {
        //TODO 更新银行账户信息

        //更新连锁店基本信息
        return chainService.update(dto);
    }

    @Override
    public List<ChainDTO> findPage(ChainQuery query) {
        //获得连锁基本信息
        List<ChainDTO> chainDTOS = chainService.findPage(query);
        if(CollectionUtil.isEmpty(chainDTOS)){
            return null;
        }
        //获取连锁上一级信息
        // 得到所有连锁id
        List<Long> idList = chainDTOS.stream().map(ChainDTO::getId).collect(Collectors.toList());
        ChainQuery parentQuery = new ChainQuery();
        parentQuery.setIds(idList);
        List<ChainDTO> parentChainDTOS = chainService.findPage(parentQuery);
        // id->连锁的map关系
        Map<Long, List<ChainDTO>> parentCollect =
                parentChainDTOS.stream().collect(Collectors.groupingBy(ChainDTO::getId));
        chainDTOS.forEach(m -> {
            // 根据id对应设置attachmentPath字段
            List<ChainDTO> dos = parentCollect.get(m.getParentId());
            if (CollectionUtil.isEmpty(dos)) {
                m.setParentName("");
            } else {
                ChainDTO chainDTO = dos.get(0);
                m.setParentName(chainDTO.getChainName() == null ? "" :
                        chainDTO.getChainName());
            }
        });
        return chainDTOS;
    }
}
