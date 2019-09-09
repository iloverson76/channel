package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.ChainBusinessService;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountQuery;
import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.bank.ChainBankDTO;
import com.deepexi.channel.domain.chain.*;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;
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
    public Long insertChain(ChainDetailDTO dto) {
        //新增连锁基本信息
        Long result = chainService.create(dto);
        dto.setId(result);
        //批量新增连锁账户信息
        this.saveChainAccountBrach(dto);
        return result;
    }

    @Override
    public ChainDetailDTO getChain(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        //查询分类
        ChainDTO chainDTO = chainService.detail(id);
        if(chainDTO == null){
            return null;
        }
        //转换成详情类
        ChainDetailDTO dto = chainDTO.clone(ChainDetailDTO.class, CloneDirection.OPPOSITE);

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

        BankAccountQuery bankAccountQuery = BankAccountQuery.builder().ids(bankAccountIds).build();
        List<BankAccountDTO> bankAccountDTOS = bankAccountService.findList(bankAccountQuery);

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
        //删除合法

        return chainService.delete(ids);
    }

    @Override
    public Boolean deleteVerification(List<Long> ids) {
        //判断连锁删除是否合法,是否具有子节点
        if(chainService.haveChildren(ids)){
            throw new ApplicationException(ResultEnum.HAVE_CHILDREN);
        }
        //TODO 删除的连锁是否被其他门店所关联
        return true;
    }

    @Override
    @Transactional
    public Boolean updateChain(ChainDetailDTO dto) {
        //删除旧的关联账户
        chainBankService.deleteByChainId(dto.getId());
        //新增所有账号
        this.saveChainAccountBrach(dto);
        //更新连锁店基本信息
        return chainService.update(dto);
    }

    @Override
    public List<ChainDetailDTO> findPage(ChainQuery query) {
        //获得连锁基本信息
        List<ChainDTO> chainDTOS = chainService.findPage(query);
        if(CollectionUtil.isEmpty(chainDTOS)){
            return null;
        }
        List<ChainDetailDTO> chainDetailDTOS = ObjectCloneUtils.convertList(chainDTOS,ChainDetailDTO.class, CloneDirection.OPPOSITE);
        //获取连锁上一级信息
        // 得到所有连锁id
        List<Long> idList = chainDetailDTOS.stream().map(ChainDetailDTO::getId).collect(Collectors.toList());
        ChainQuery parentQuery = new ChainQuery();
        parentQuery.setPage(-1);
        parentQuery.setIds(idList);
        List<ChainDTO> parentChainDTOS = chainService.findPage(parentQuery);
        // id->连锁的map关系
        Map<Long, List<ChainDTO>> parentCollect =
                parentChainDTOS.stream().collect(Collectors.groupingBy(ChainDTO::getId));
        //获取连锁类型信息
        //得到所有连锁类型信息
        List<Long> chainTypeIds = chainDetailDTOS.stream().map(ChainDetailDTO::getChainTypeId).collect(Collectors.toList());
        ChainTypeQuery typeQuery = new ChainTypeQuery();
        typeQuery.setIds(chainTypeIds);
        typeQuery.setPage(-1);
        List<ChainTypeDTO> chainTypeDTOS = chainTypeService.findPage(typeQuery);
        //chainTypeId->连锁类型的map关系
        Map<Long, List<ChainTypeDTO>> chainTypeCollect =
                chainTypeDTOS.stream().collect(Collectors.groupingBy(ChainTypeDTO::getId));

        //拼接父级节点信息、类型信息
        chainDetailDTOS.forEach(m -> {
            // 根据id对应设置父级名称字段
            List<ChainDTO> dos = parentCollect.get(m.getParentId());
            if (CollectionUtil.isEmpty(dos)) {
                m.setParentName("");
            } else {
                ChainDTO chainDTO = dos.get(0);
                m.setParentName(chainDTO.getChainName() == null ? "" :
                        chainDTO.getChainName());
            }

            //根据chainTypeId对应设置类型名称字段
            List<ChainTypeDTO> dos2 =  chainTypeCollect.get(m.getChainTypeId());
            if(CollectionUtil.isEmpty(dos2)){
                m.setChainTypeName("");
            } else {
                ChainTypeDTO chainTypeDTO = dos2.get(0);
                m.setChainTypeName(chainTypeDTO.getChainTypeName() == null?"":chainTypeDTO.getChainTypeName());
            }
        });

        return chainDetailDTOS;
    }

    /**
     * @MethodName: saveChainAccountBrach
     * @Description: 批量保存连锁账号，银行账号
     * @Param: [dto]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/1
    **/
    private boolean saveChainAccountBrach(ChainDetailDTO dto){
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
            chainBankDTOS.add(chainBankDTO);
        }
        boolean insertChainBankResult = chainBankService.saveBatch(chainBankDTOS);
        return insertChainBankResult;
    }

    /**
     * @MethodName: recursionTree
     * @Description: 递归更新所有儿子节点的path以及parentId，返回所有需要的dto列表
     * @Param: [dto]
     * @Return: java.util.List<com.deepexi.channel.domain.chain.ChainDTO>
     * @Author: mumu
     * @Date: 2019/9/5
    **/
    public List<ChainDTO> recursionTree(ChainTreeDTO dto){
        //批量更新所有节点,根据id更新parent_id和path，返回所有孙子儿子节点
        List<ChainDTO> saveChainDTOS =  new LinkedList<>();
        //遍历更新儿子节点
        List<ChainTreeDTO>  childrenList = dto.getChildren();
        if(CollectionUtil.isEmpty(childrenList)){
            return saveChainDTOS;
        }
        childrenList.forEach(c ->{
            c.setParentId(dto.getId());
            c.setPath(dto.getPath()+"/"+c.getId());
            saveChainDTOS.add(c);
            //递归，把儿子的所有节点的parent_id和path都更新
            saveChainDTOS.addAll(this.recursionTree(c));
        });
        return saveChainDTOS;
    }

    /**
     * @MethodName: saveTree
     * @Description: 批量更新整棵树的节点
     * @Param: [dtos]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/5
    **/
    @Override
    @Transactional
    public Boolean saveTree(List<ChainTreeDTO> dtos){
        // 将所有的parentId设置为0，path设置为""
        Boolean reset = chainService.resetTree();

        //全部节点列表，用于批量更新
        List<ChainDTO> chainDTOS = new LinkedList<>();

        //遍历每一个根节点，获取需要更新的全部节点
        dtos.forEach(dto ->{
            dto.setPath("/"+dto.getId());
            //根节点parentId都是0
            dto.setParentId(0L);
            //根节点加入更新列表
            chainDTOS.add(dto);
            //根节点的所有子节点加入更新列表
            chainDTOS.addAll(this.recursionTree(dto));
        });

        //批量更新所有节点的parentId与path
        return chainService.updateBatch(chainDTOS);
    }

    /**
     * @MethodName: getTree
     * @Description: 获取整个连锁树形结构
     * @Param: []
     * @Return: java.util.List<com.deepexi.channel.domain.chain.ChainTreeDTO>
     * @Author: mumu
     * @Date: 2019/9/5
    **/
    @Override
    public List<ChainTreeDTO> getTree() {
        //树形结构结果，可能有多个树（多个根节点）
        List<ChainTreeDTO> result  = new LinkedList<>();

        //获取所有树的节点，根据path获取三级
        //增量就显示三级
//        List<ChainDTO> chainDTOS = chainService.getChainTreeNode();
        //全量需要全部数据
        ChainQuery query = new ChainQuery();
        query.setPage(-1);
        query.setPath("/");
        List<ChainDTO> chainDTOS = chainService.findPage(query);

        List<ChainTreeDTO> chainTreeDTOS = ObjectCloneUtils.convertList(chainDTOS, ChainTreeDTO.class);
        Map<Long,ChainTreeDTO> chainTreeMap = chainTreeDTOS.stream().collect(Collectors.toMap(ChainTreeDTO::getId,c->c));

        //查询得到所有连锁类型名称
        List<ChainTypeDTO> chainTypeDTOS = this.getChainTypeListByChainDTOS(chainDTOS);
        Map<Long,ChainTypeDTO> chainTypeDTOMap = chainTypeDTOS.stream().collect(Collectors.toMap(ChainTypeDTO::getId,c->c));

        //for循环拼接类型名称以及儿子节点
        chainTreeDTOS.forEach(c ->{
            c.setChainTypeName(chainTypeDTOMap.get(c.getChainTypeId()) == null ? "" : chainTypeDTOMap.get(c.getChainTypeId()).getChainTypeName());
            if(c.getParentId() == 0L ){
                result.add(c);
            }else{
                ChainTreeDTO parentDTO = chainTreeMap.get(c.getParentId());
                if(parentDTO.getChildren() == null){
                    parentDTO.setChildren(new LinkedList<>());
                }
                parentDTO.getChildren().add(c);
            }
        });
        return result;
    }

    /**
     * @MethodName: getChildren
     * @Description: 根据id获取所有儿子节点, 传0表示查询所有根节点
     * @Param: [id]
     * @Return: java.util.List<com.deepexi.channel.domain.chain.ChainTreeDTO>
     * @Author: mumu
     * @Date: 2019/9/5
    **/
    @Override
    public List<ChainTreeDTO> getChildren(Long id) {
        ChainQuery query = new ChainQuery();
        query.setPage(-1);
        query.setParentId(id);
        //如果id为0，查询所有根节点, 根节点path不为空, 非根节点为空
        if(id == 0L){
            query.setPath("/");
        }
        List<ChainDTO> chainDTOS = chainService.findPage(query);
        if(CollectionUtil.isEmpty(chainDTOS)){
            return null;
        }
        List<ChainTreeDTO> chainTreeDTOS = ObjectCloneUtils.convertList(chainDTOS, ChainTreeDTO.class);
        //查询连锁类型列表
        List<ChainTypeDTO> chainTypeDTOS = this.getChainTypeListByChainDTOS(chainDTOS);
        Map<Long,ChainTypeDTO> chainTypeDTOMap = chainTypeDTOS.stream().collect(Collectors.toMap(ChainTypeDTO::getId,c->c));
        //拼接连锁类型到列表中
        chainTreeDTOS.forEach(c->{
           c.setChainTypeName(chainTypeDTOMap.get(c.getChainTypeName())==null?"":chainTypeDTOMap.get(c.getChainTypeId()).getChainTypeName());
        });
        return chainTreeDTOS;
    }

    /**
     * @MethodName: getChainTypeListByChainDTOS
     * @Description: 根据chainTypeId 获取chainTypeList
     * @Param: [chainDTOS]
     * @Return: java.util.List<com.deepexi.channel.domain.chain.ChainTypeDTO>
     * @Author: mumu
     * @Date: 2019/9/9
    **/
    public List<ChainTypeDTO> getChainTypeListByChainDTOS(List<ChainDTO> chainDTOS){
        List<Long> chainTypeIds = chainDTOS.stream().map(ChainDTO::getChainTypeId).collect(Collectors.toList());
        ChainTypeQuery query = ChainTypeQuery.builder().ids(chainTypeIds).build();
        List<ChainTypeDTO> chainTypeDTOS = chainTypeService.findPage(query);
        return chainTypeDTOS;
    }

}
