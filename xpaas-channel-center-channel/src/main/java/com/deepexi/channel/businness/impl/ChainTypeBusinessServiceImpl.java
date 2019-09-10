package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.ChainTypeBusinessService;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.ChainService;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChainTypeBusinessServiceImpl implements ChainTypeBusinessService {

    @Autowired
    ChainTypeService chainTypeService;
    @Autowired
    ChainService chainService;

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

    /**
     * @MethodName: getLegalParentChainType
     * @Description: 获取合法的分类节点
     * @Param: [id]
     * @Return: java.util.List<com.deepexi.channel.domain.chain.ChainTypeDTO>
     * @Author: mumu
     * @Date: 2019/9/10
    **/
    @Override
    public List<ChainTypeDTO> getLegalParentChainType(Long id) {
        //查询所有限制上级的父级id，这些分类不能被设置上级
        ChainTypeQuery query = ChainTypeQuery.builder().limitParent(1).build();
        List<ChainTypeDTO> list = chainTypeService.findPage(query);
        //排除掉的id
        List<Long> excludeIds = list.stream().map(ChainTypeDTO::getParentId).collect(Collectors.toList());
        //本身也要排除
        excludeIds.add(id);

        ChainTypeQuery query2 = ChainTypeQuery.builder().excludeIds(excludeIds).build();
        return chainTypeService.findPage(query2);
    }
}
