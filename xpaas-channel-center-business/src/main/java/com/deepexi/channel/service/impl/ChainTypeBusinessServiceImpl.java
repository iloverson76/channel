package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.ChainService;
import com.deepexi.channel.service.ChainTypeBusinessService;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.channel.service.StoreChainService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
public class ChainTypeBusinessServiceImpl implements ChainTypeBusinessService {

    @Autowired
    ChainTypeService chainTypeService;
    @Autowired
    ChainService chainService;
    @Autowired
    StoreChainService storeChainService;

    /**
     * @MethodName:
     * @Description: 批量删除连锁类型
     * @Param:
     * @Return:
     * @Author: mumu
     * @Date: 2019/8/30
     **/
    @Override
    public boolean deleteChainType(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        //判断是否节点是否具有儿子节点,并且不在ids中
        if(chainTypeService.haveChildren(ids)){
            throw new ApplicationException(ResultEnum.HAVE_CHILDREN);
        }
        //判断是否有被连锁引用
        ChainQuery chainQuery = ChainQuery.builder().chainTypeIdList(ids).build();
        List<ChainDTO> list = chainService.findPage(chainQuery);

        if(CollectionUtil.isNotEmpty(list)){
            throw new ApplicationException(ResultEnum.HAVE_RELATION);
        }
        return chainTypeService.delete(ids);
    }

    /**
     * @MethodName: haveChainRelation
     * @Description: 是否拥有连锁店关联该类型
     * @Param: [dto]
     * @Return: boolean false 表示没有关联， true 表示有关联
     * @Author: mumu
     * @Date: 2019/8/30
     **/
    @Override
    public boolean haveChainRelation(List<Long> ids) {
        ChainQuery chainQuery = ChainQuery.builder().chainTypeIdList(ids).build();
        List<ChainDTO> chainDTOS = chainService.findPage(chainQuery);
        if (CollectionUtil.isEmpty(chainDTOS)){
            return false;
        }
        return true;
    }

    @Override
    public List<ChainTypeDTO> findPage(ChainTypeQuery query) {
        if (query.getPage() != null && query.getPage() != -1) {
            List<ChainTypeDTO> chainTypeDTOS = chainTypeService.findPage(query);

            // 得到所有连锁类型id
            List<Long> idList = chainTypeDTOS.stream().map(ChainTypeDTO::getId).collect(Collectors.toList());
            ChainTypeQuery parentQuery = ChainTypeQuery.builder().ids(idList).build();
            parentQuery.setPage(-1);
            List<ChainTypeDTO> parentChainTypeDTOS = chainTypeService.findPage(parentQuery);
            if(CollectionUtil.isEmpty(parentChainTypeDTOS)){
                return chainTypeDTOS;
            }
            // id->连锁类型的map关系
            Map<Long, List<ChainTypeDTO>> parentCollect =
                    parentChainTypeDTOS.stream().collect(Collectors.groupingBy(ChainTypeDTO::getId));
            chainTypeDTOS.forEach(m -> {
                // 根据id对应设置attachmentPath字段
                List<ChainTypeDTO> dtos = parentCollect.get(m.getParentId());
                if (CollectionUtil.isEmpty(dtos)) {
                    m.setParentName("");
                } else {
                    ChainTypeDTO chainTypeDTO = dtos.get(0);
                    m.setParentName(chainTypeDTO.getChainTypeName() == null ? "" :
                            chainTypeDTO.getChainTypeName());
                }
            });
            return chainTypeDTOS;
        }else{
            return chainTypeService.findAll(query);
        }
    }

//    @Override
//    public List<ChainTypeDTO> getLegalParentChainType(Long id) {
//        //查询所有限制上级的父级id，这些分类不能被设置上级
//        ChainTypeQuery query = ChainTypeQuery.builder().limitParent(1).build();
//        query.setPage(-1);
//        List<ChainTypeDTO> list = chainTypeService.findPage(query);
//        //排除掉的id
//        List<Long> excludeIds = list.stream().map(ChainTypeDTO::getParentId).collect(Collectors.toList());
//        //本身也要排除
//        excludeIds.add(id);
//
//        ChainTypeQuery query2 = ChainTypeQuery.builder().excludeIds(excludeIds).build();
//        query2.setPage(-1);
//        return chainTypeService.findPage(query2);
//    }

    @Override
    @Transactional
    public Long  create(ChainTypeDTO dto) {
        //先保存基本信息
        Long id = chainTypeService.create(dto);
        log.info("新增连琐类型，id= {}",id);
        dto.setId(id);
        //得到id后拼接path与rootId
        //判断是否限制上级，限制了就需要处理path
        if(dto.getLimitParent().equals(1)){
            ChainTypeDTO parentDTO = chainTypeService.detail(dto.getParentId());
            dto.setPath(parentDTO.getPath()+"/"+id);
            dto.setLinkId(parentDTO.getLinkId());
        }else{
            //没限制上级就为根节点
            dto.setLinkId(id);
            dto.setPath("/"+id);
        }
        //修改path与rootid
        chainTypeService.update(dto);
        return id;
    }

    @Override
    public Boolean update(ChainTypeDTO dto) {
        //查询是否限制上级，限制了就需要拼接path
        if(dto.getLimitParent().equals(1)){
            ChainTypeDTO parentDTO = chainTypeService.detail(dto.getParentId());
            dto.setPath(parentDTO.getPath()+"/"+dto.getId());
            dto.setLinkId(parentDTO.getLinkId());
        }else{
            //没限制上级就为根节点
            dto.setLinkId(dto.getId());
            dto.setPath("/"+dto.getId());
        }
        //更新节点需要更新下级path和root_id
        //查询所有下级节点
        ChainTypeQuery query = ChainTypeQuery.builder().path("/"+dto.getId()+"/").build();
        query.setPage(-1);
        List<ChainTypeDTO> list = chainTypeService.findPage(query);
        //list为空，表示没有子节点，更新本身节点信息即可
        if(CollectionUtil.isEmpty(list)){
            log.info("更新连琐类型，dto = {}",dto);
            return chainTypeService.update(dto);
        }
        //list不为空，需要更新子节点的path与rootId
        for(ChainTypeDTO d: list){
            d.setLinkId(dto.getLinkId());
            //设置path，取父节点后面那一段拼接父级path
            String[] ss = d.getPath().split("/"+dto.getId());
            d.setPath(dto.getPath()+ss[1]);
        }
        list.add(dto);
        //批量更新
        return chainTypeService.updateBatch(list);
    }
    /**
     * @MethodName: parentChainType
     * @Description: 树形结构中，根据连琐类型id获取合法的上级类型
     * @Param: [chainTypeId]
     * @Return: com.deepexi.channel.domain.chain.ChainDTO
     * @Author: mumu
     * @Date: 2019/9/12
     **/
    @Override
    public List<ChainTypeDTO> parentChainType(Long chainTypeId) {
        ChainTypeDTO chainTypeDTO = chainTypeService.detail(chainTypeId);
        if(null == chainTypeDTO){
            return null;
        }
        log.info("根据连锁类型id获取详情",chainTypeDTO);
        //如果该分类限制上级，则上级只有他的父亲
        if(chainTypeDTO.getLimitParent() == 1){
            ChainTypeDTO parent = chainTypeService.detail(chainTypeDTO.getParentId());
            log.info("连锁类型被限制上级",parent);
            if(null == parent){
                return null;
            }
            List<ChainTypeDTO> result = new LinkedList<>();
            result.add(parent);
            return result;
        }
        //如果该分类没有限制上级 排除本身，排除所有被限制的上级节点
        //查出所有节点
        ChainTypeQuery query = new ChainTypeQuery();
        List<ChainTypeDTO> list = chainTypeService.findPage(query);
        //排查被限制上级的节点和本身节点
        List<Long> idNotIn = new LinkedList<>();
        idNotIn.add(chainTypeId);
        list.forEach(c->{
            if(c.getLimitParent() == 1){
                idNotIn.add(c.getParentId());
            }
        });
        ChainTypeQuery chainTypeQuery = new ChainTypeQuery();
        chainTypeQuery.setExcludeIds(idNotIn);
        List<ChainTypeDTO> chainTypeDTOS = chainTypeService.findAll(chainTypeQuery);
        return chainTypeDTOS;
    }

    /**(
     * @MethodName: haveRelation
     * @Description: 修改连琐类型时，若属于该连琐类型的连琐在树中或者被门店关联，则具有联系
     * @Param: [dto]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/19
    **/
    @Override
    public boolean haveRelation(ChainTypeDTO dto) {
        ChainQuery chainQuery = ChainQuery.builder().chainTypeId(dto.getId()).build();
        List<ChainDTO> chainDTOS = chainService.findPage(chainQuery);
        if(CollectionUtil.isEmpty(chainDTOS)){
            return false;
        }
        //判断属于该类型的连琐是否在树形结构中
        for (ChainDTO c : chainDTOS) {
            if(StringUtil.isNotEmpty(c.getPath())){
               return true;
            }
        }
        //判断该连琐类型的所有连琐是否关联了门店
        Set<Long> chainIds = chainDTOS.stream().map(ChainDTO::getId).collect(Collectors.toSet());
        StoreChainQuery storeChainQuery = StoreChainQuery.builder().chainIds(new LinkedList<>(chainIds)).build();
        List<StoreChainDTO> storeChainDTOS = storeChainService.findList(storeChainQuery);
        if(CollectionUtil.isNotEmpty(storeChainDTOS)){
            return true;
        }
        return false;
    }

    @Override
    public List<ChainTypeDTO> getListChainType(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)){
            List<ChainTypeDTO> list = chainTypeService.findAll(new ChainTypeQuery());
            return list;
        }
        ChainTypeQuery query = new ChainTypeQuery();
        query.setPage(-1);
        query.setIds(ids);
        List<ChainTypeDTO> list = chainTypeService.findAll(query);
        log.info("区域链路",list);
        if (CollectionUtil.isEmpty(list)){
            return new ArrayList<>();
        }
        Set<Long> set = new HashSet<>();
        for (ChainTypeDTO chainTypeDTO : list) {
            String path = chainTypeDTO.getPath();
            List<Long> idList = Arrays.stream(path.split("/")).filter(StringUtil::isNotEmpty).map(Long::parseLong).collect(Collectors.toList());
            set.add(idList.get(0));
        }
        List<Long> linkIdList = new ArrayList<>(set);
        List<ChainTypeDTO> ChainTypeDTO = chainTypeService.findByChainIdNotInAll(linkIdList);
        return ChainTypeDTO;
    }
}
