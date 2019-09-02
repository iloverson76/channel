package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.ChainBusinessService;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.bank.ChainBankDTO;
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
    @Autowired
    BankService bankService;

    @Override
    @Transactional
    public Long insertChain(ChainDTO dto) {
        //校验编码是否重复
        if(!chainService.isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //新增连锁基本信息
        Long result = chainService.create(dto);
        dto.setId(result);
        //批量新增连锁账户信息
        this.saveChainAccountBrach(dto);
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
        //查询所有账户id
        List<ChainBankDTO> chainBankDTOS = chainBankService.getChainBankByChainId(id);
        if(CollectionUtil.isEmpty(chainBankDTOS)){
            return dto;
        }
        //查询所有账户详细信息
        List<Long> bankAccountIds = chainBankDTOS.stream().map(ChainBankDTO::getBankAccountId).collect(Collectors.toList());
        List<BankAccountDTO> bankAccountDTOS = bankAccountService.getBankAccountByIds(bankAccountIds);

        //查询所有账户所属银行
        List<Long> bankIds = bankAccountDTOS.stream().map(BankAccountDTO::getBankId).collect(Collectors.toList());
        List<BankDTO> bankDTOS = bankService.getBankByIds(bankIds);

        //拼接账户跟银行信息
        Map<Long, List<BankDTO>> bankMap =
                bankDTOS.stream().collect(Collectors.groupingBy(BankDTO::getId));
        bankAccountDTOS.forEach(b ->{
            List<BankDTO> bank = bankMap.get(b.getBankId());
            b.setBankName(bank.get(0).getBankName());
        });

        //拼接账户列表到银行中
        dto.setBankAccountList(bankAccountDTOS);

        return dto;
    }

    @Override
    public Boolean deleteChain(List<Long> ids) {
        //判断连锁删除是否合法,是否具有子节点
        if(chainService.haveChildren(ids)){
            throw new ApplicationException(ResultEnum.HAVE_CHILDREN);
        }
        //TODO 删除的连锁是否被其他门店所关联

        //删除合法
        return chainService.delete(ids);
    }

    @Override
    @Transactional
    public Boolean updateChain(ChainDTO dto) {
        //判断编码是否重复
        if(!chainService.isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //TODO 更新银行账户信息
        //删除旧的关联账户
        chainBankService.deleteByChainId(dto.getId());
        //新增所有账号
        this.saveChainAccountBrach(dto);
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
            // 根据id对应设置父级名称字段
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

    /**
     * @MethodName: saveChainAccountBrach
     * @Description: 批量保存连锁账号，银行账号
     * @Param: [dto]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/1
    **/
    private boolean saveChainAccountBrach(ChainDTO dto){
        List<BankAccountDTO> bankAccountDTOS = dto.getBankAccountList();
        if(CollectionUtil.isEmpty(bankAccountDTOS)){
            return true;
        }
        bankAccountDTOS = bankAccountService.saveBatch(bankAccountDTOS);

        //批量新增账户、连锁关联信息
        List<ChainBankDTO> chainBankDTOS = new ArrayList<>();
        for(BankAccountDTO bankAccount:bankAccountDTOS){
            ChainBankDTO chainBankDTO = ChainBankDTO.builder()
                    .bankAccountId(bankAccount.getId())
                    .chainId(dto.getId())
                    .build();
            //TODO 下面这段代码设置租户id等是否必要，没设置会报不能为空
            chainBankDTO.setTenantId(dto.getTenantId());
            chainBankDTO.setAppId(dto.getAppId());
            chainBankDTO.setVersion(dto.getVersion());
            chainBankDTO.setUpdatedBy(dto.getUpdatedBy());
            chainBankDTO.setCreatedBy(dto.getCreatedBy());
            chainBankDTO.setCreatedTime(dto.getCreatedTime());
            chainBankDTO.setUpdatedTime(dto.getUpdatedTime());

            chainBankDTOS.add(chainBankDTO);
        }
        boolean insertChainBankResult = chainBankService.saveBatch(chainBankDTOS);
        return insertChainBankResult;
    }

}
