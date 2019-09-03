package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.ChainTypeBusinessService;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.ChainService;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Integer count = chainService.getChainCountByTypeIds(ids);
        if(count > 0){
            throw new ApplicationException(ResultEnum.HAVE_RELATION);
        }
        return chainTypeService.delete(ids);
    }

    /**
     * @MethodName: haveChainRelation
     * @Description: 是否拥有连锁店关联该类型
     * @Param: [dto]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/8/30
     **/
//    private boolean haveChainRelation(ChainTypeDTO dto){
//        return true;
//    }
}
