package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    @Autowired
    StoreChainService storeChainService;

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
            dto.setLimitParent(chainTypeDTO.getLimitParent());
        }

        //查询连锁的所有账户，获取账户信息
        //查询所有账户id
        ChainBankQuery chainBankQuery = ChainBankQuery.builder().chainId(id).build();
        List<ChainBankDTO> chainBankDTOS = chainBankService.findList(chainBankQuery);
        if(CollectionUtil.isEmpty(chainBankDTOS)){
            return dto;
        }
        //查询所有账户详细信息
        List<Long> bankAccountIds = chainBankDTOS.stream().map(ChainBankDTO::getBankAccountId).collect(Collectors.toList());

        BankAccountQuery bankAccountQuery = BankAccountQuery.builder().ids(bankAccountIds).build();
        List<BankAccountDTO> bankAccountDTOS = bankAccountService.findList(bankAccountQuery);

        //查询所有账户所属银行
        Set<Long> bankIds = bankAccountDTOS.stream().map(BankAccountDTO::getBankId).collect(Collectors.toSet());
        BankQuery bankQuery = BankQuery.builder().ids(new LinkedList<>(bankIds)).build();
        List<BankDTO> bankDTOS = bankService.findList(bankQuery);

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
        // 删除的连锁是否被其他门店所关联
        StoreChainQuery storeChainQuery = StoreChainQuery.builder().chainIds(ids).build();
        List<StoreChainDTO> storeChainDTOS = storeChainService.findList(storeChainQuery);
        if(CollectionUtil.isNotEmpty(storeChainDTOS)){
            throw new ApplicationException(ResultEnum.HAVE_RELATION);
        }
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
        Map<Long, ChainDTO> parentCollect =
                parentChainDTOS.stream().collect(Collectors.toMap(ChainDTO::getId, a -> a,(k1,k2)->k1));
        //获取连锁类型信息
        //得到所有连锁类型信息
        List<Long> chainTypeIds = chainDetailDTOS.stream().map(ChainDetailDTO::getChainTypeId).collect(Collectors.toList());
        ChainTypeQuery typeQuery = new ChainTypeQuery();
        typeQuery.setIds(chainTypeIds);
        typeQuery.setPage(-1);
        List<ChainTypeDTO> chainTypeDTOS = chainTypeService.findPage(typeQuery);
        //chainTypeId->连锁类型的map关系
        Map<Long, ChainTypeDTO> chainTypeCollect =
                chainTypeDTOS.stream().collect(Collectors.toMap(ChainTypeDTO::getId, a -> a,(k1,k2)->k1));

        //拼接父级节点信息、类型信息
        chainDetailDTOS.forEach(m -> {
            // 根据id对应设置父级名称字段
            ChainDTO dos = parentCollect.get(m.getParentId());
            if ( dos == null) {
                m.setParentName("");
            } else {
                m.setParentName(dos.getChainName() == null ? "" :
                        dos.getChainName());
                m.setParentChainTypeId(dos.getChainTypeId());
            }

            //根据chainTypeId对应设置类型名称字段
            ChainTypeDTO dos2 =  chainTypeCollect.get(m.getChainTypeId());
            if(dos2 == null){
                m.setChainTypeName("");
                m.setLimitParent(null);
            } else {
                m.setChainTypeName(dos2.getChainTypeName() == null?"":dos2.getChainTypeName());
                m.setLimitParent(dos2.getLimitParent());
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
     * @Description: 递归更新所有儿子节点的path以及parentId，返回儿子孙子的dto列表，不包含根节点即dto本身
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
        return chainService.updatePathAndParentIdBatch(chainDTOS);
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
        if(CollectionUtil.isEmpty(chainDTOS)){
            return null;
        }
        List<ChainTreeDTO> chainTreeDTOS = ObjectCloneUtils.convertList(chainDTOS, ChainTreeDTO.class);
        Map<Long,ChainTreeDTO> chainTreeMap = chainTreeDTOS.stream().collect(Collectors.toMap(ChainTreeDTO::getId,c->c));

        //查询得到所有连锁类型名称
        List<ChainTypeDTO> chainTypeDTOS = this.getChainTypeListByChainDTOS(chainDTOS);
        Map<Long,ChainTypeDTO> chainTypeDTOMap = chainTypeDTOS.stream().collect(Collectors.toMap(ChainTypeDTO::getId,c->c));

        //for循环拼接类型名称以及儿子节点
        chainTreeDTOS.forEach(c ->{
            c.setChainTypeName(chainTypeDTOMap.get(c.getChainTypeId()) == null ? "" : chainTypeDTOMap.get(c.getChainTypeId()).getChainTypeName());
            //parentId为0，那说明是根节点
            if(c.getParentId() == 0L ){
                result.add(c);
            }else{
                ChainTreeDTO parentDTO = chainTreeMap.get(c.getParentId());
                if(parentDTO != null){
                    if(parentDTO.getChildren() == null){
                        parentDTO.setChildren(new LinkedList<>());
                    }
                    parentDTO.getChildren().add(c);
                }
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
        query.setPage(-1);
        List<ChainTypeDTO> chainTypeDTOS = chainTypeService.findPage(query);
        return chainTypeDTOS;
    }

    @Override
    @Transactional
    public Boolean updateTreeNode(ChainDTO chainDTO) {
        if(chainDTO == null){
            return null;
        }
        //查询父节点,用于后面设置path
        ChainDTO rootParent = null;
        //如果设置为根节点
        if(chainDTO.getParentId()==0){
            /**设置rootParent节点id为0，path为""，方便后面代码拼接,无需再判断是否设置为根节点*/
            rootParent = new ChainDTO();
            rootParent.setId(0L);
            rootParent.setPath("");
        }else{
            //有父级节点
           rootParent = chainService.detail(chainDTO.getParentId());
        }
        //查询该节点的所有子节点
        ChainQuery query = ChainQuery.builder().path("/"+chainDTO.getId()+"/").build();
        query.setPage(-1);
        List<ChainDTO> children = chainService.findPage(query);
        //没有子节点,直接更新这个节点的path和parentId即可
        if(CollectionUtil.isEmpty(children)){
            //设置path
            chainDTO.setPath(rootParent.getPath()+"/"+chainDTO.getId());
            return chainService.updatePathAndParentId(chainDTO);
        }
        //有子节点,需要更新子节点的所有path，拼接成树形结构, 加入本身节点到children中，后面更新path和parentId
        children.add(chainDTO);
        List<ChainTreeDTO> chainTreeDTOS = ObjectCloneUtils.convertList(children, ChainTreeDTO.class);
        Map<Long,ChainTreeDTO> chainTreeMap = chainTreeDTOS.stream().collect(Collectors.toMap(ChainTreeDTO::getId,c->c));

        /**更新该节点的parentId、path以及所有儿子孙子节点parentId和path*/
        //遍历拼接成树形结构
        //根节点
        ChainTreeDTO root = null;
        for(ChainTreeDTO c :chainTreeDTOS){
            //寻找根节点
            if( c.getId().equals(chainDTO.getId())){
                //是否有父级已经在前面处理
                root = c;
                root.setParentId(rootParent.getId());
                root.setPath(rootParent.getPath()+"/"+root.getId());
            }else{
                ChainTreeDTO parentDTO = chainTreeMap.get(c.getParentId());
                if(parentDTO != null){
                    if(parentDTO.getChildren() == null){
                        parentDTO.setChildren(new LinkedList<>());
                    }
                    parentDTO.getChildren().add(c);
                }
            }
        }
        //设置path和parentId，得到孙子儿子的结果列表
        List<ChainDTO> recursionResult = this.recursionTree(root);
        recursionResult.add(root.clone(ChainDTO.class));//根节点也要设置进去

        /**保存结果*/
        //批量更新所有节点的parentId与path
        return chainService.updatePathAndParentIdBatch(recursionResult);
    }

    @Override
    public Boolean deleteTreeNode(Long id) {
        //获取所有子节点以及节点本身
        ChainQuery query = ChainQuery.builder().path("/"+id).build();
        List<ChainDTO> dtos = chainService.findPage(query);
        //批量设置path为空,parentId为0
        for( ChainDTO c : dtos){
            c.setPath("");
            c.setParentId(0L);
        }
        return chainService.updatePathAndParentIdBatch(dtos);
    }

    @Override
    public Boolean addTreeNode(ChainDTO chainDTO) {
        if(chainDTO.getParentId().equals( 0L)){
            //parentId为0，代表该节点设置为根节点
            chainDTO.setPath("/"+chainDTO.getId());
        }else{
            //查询父节点
            ChainDTO parent = chainService.detail(chainDTO.getParentId());
            chainDTO.setPath(parent.getPath()+"/"+chainDTO.getId());
        }
        return chainService.updatePathAndParentId(chainDTO);
    }

    /**
     * @MethodName: getLegalParentChainByChainId
     * @Description: 根据连琐类型id获取合法的上级节点列表
     * @Param: [chainId]
     * @Return: java.util.List<com.deepexi.channel.domain.chain.ChainDTO>
     * @Author: mumu
     * @Date: 2019/9/12
    **/
    @Override
    public List<ChainDTO> getLegalParentChainByChainId(Long chainTypeId) {
        //查询已经在树中，类型为chainTypeId的节点
        ChainQuery query = new ChainQuery();
        query.setChainTypeId(chainTypeId);
        //path不为空的，就是在树中的节点
        query.setPath("/");
        return chainService.findPage(query);
    }

    /**
     * @MethodName: isChangeChainTypeLegal
     * @Description: 修改连琐的类型时，如果在树中不能修改，如果被门店关联也不能修改
     * @Param: [dto]
     * @Return: boolean true合法 false 非法
     * @Author: mumu
     * @Date: 2019/9/18
    **/
    @Override
    public boolean isChangeChainTypeLegal(ChainDetailDTO dto) {
        ChainDTO historyDTO = chainService.detail(dto.getId());
        if(historyDTO == null){
            return false;
        }
        //在树中，修改非法
        if(StringUtil.isNotEmpty(historyDTO.getPath())){
            return false;
        }
        //判断是否被门店关联，关联则修改非法
        StoreChainQuery storeChainQuery = StoreChainQuery.builder().chainId(dto.getId()).build();
        List<StoreChainDTO> storeChainDTOS = storeChainService.findList(storeChainQuery);
        if(CollectionUtil.isNotEmpty(storeChainDTOS)){
            return false;
        }
        return true;
    }

}
