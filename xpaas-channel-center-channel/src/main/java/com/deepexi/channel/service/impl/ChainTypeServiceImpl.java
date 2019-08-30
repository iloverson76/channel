package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.channel.dao.ChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcChainType;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.channel.mapper.ChainTypeMapper;

import java.util.*;
import java.util.stream.Collectors;

import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class ChainTypeServiceImpl implements ChainTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChainTypeDAO chainTypeDAO;

    @Override
    public List<ChainTypeDTO> findPage(ChainTypeQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
            List<ChainTypeDO> pages =  chainTypeDAO.findList(query);
            List<ChainTypeDTO> chainTypeDTOS = ObjectCloneUtils.convertList(pages, ChainTypeDTO.class, CloneDirection.OPPOSITE);
            // 得到所有连锁类型id
            Set<Long> idList = chainTypeDTOS.stream().map(ChainTypeDTO::getId).collect(Collectors.toSet());
            List<ChainTypeDO> parentChainTypeDOS = chainTypeDAO.selectListByIds(idList);
            // id->连锁类型的map关系
            Map<Long, List<ChainTypeDO>> parentCollect =
                    parentChainTypeDOS.stream().collect(Collectors.groupingBy(ChainTypeDO::getId));
            chainTypeDTOS.forEach(m -> {
                // 根据id对应设置attachmentPath字段
                List<ChainTypeDO> dos = parentCollect.get(m.getParentId());
                if (CollectionUtil.isEmpty(dos)) {
                    m.setParentName("");
                } else {
                    ChainTypeDO chainTypeDO = dos.get(0);
                    m.setParentName(chainTypeDO.getChainTypeName() == null ? "" :
                            chainTypeDO.getChainTypeName());
                }
            });
            return chainTypeDTOS;
        }
        return findAll(query);
    }

    @Override
    public List<ChainTypeDTO> findAll(ChainTypeQuery query) {
        List<ChainTypeDO> list = chainTypeDAO.findList(query);
        return ObjectCloneUtils.convertList(list,ChainTypeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public ChainTypeDTO detail(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        ChainTypeDO category = chainTypeDAO.getById(id);
        if (null == category) {
            return null;
        }
        return category.clone(ChainTypeDTO.class);
    }

    @Override
    public Boolean update(Long id,ChainTypeDTO dto) {
        dto.setId(id);
        //判断编码是否重复
        if(!isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //TODO 判断父级节点是否合法,是否出现环形结构

        boolean result = chainTypeDAO.updateById(dto.clone(ChainTypeDO.class));
        return result;
    }

    @Override
    public Long create(ChainTypeDTO dto) {
        //新增校验,编码不能重复
        if(!isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //插入
        ChainTypeDO chainTypeDO = dto.clone(ChainTypeDO.class);
        boolean result = chainTypeDAO.save(chainTypeDO);
        if(result){
            return chainTypeDO.getId();
        }else{
            return 0L;
        }

    }


    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return chainTypeDAO.removeByIds(ids);
    }

     /**
      * @MethodName: haveChilder
      * @Description: 是否具有儿子节点,并且不在ids中
      * @Param: [ids]
      * @Return: java.lang.Boolean
      * @Author: mumu
      * @Date: 2019/8/30
     **/
     @Override
    public Boolean haveChildren(List<Long> ids){
        //获得所有子节点
        List<ChainTypeDO> chainTypeDOS = chainTypeDAO.findParentList(ids);
        //没有子节点
        if(CollectionUtil.isEmpty(chainTypeDOS)){
            return false;
        }
        //判断子节点是否也被删除，如果子节点不被删除，则拒绝删除
        for(ChainTypeDO a : chainTypeDOS){
            if(!ids.contains(a.getId())){
                return true;
            }
        }
        //父节点跟子节点一同删除, 允许删除
        return false;
    }

    /**
     * @MethodName: isCodeUnique
     * @Description: 判断连锁类型编码是否重复
     * @Param: [code]
     * @Return: boolean 编码唯一, true 编码唯一 ， false 编码不唯一
     * @Author: mumu
     * @Date: 2019/8/30
    **/
    @Override
    public boolean isCodeUnique(ChainTypeDTO dto){
        List<ChainTypeDO> list = chainTypeDAO.list(new QueryWrapper<ChainTypeDO>().lambda()
                .eq(ChainTypeDO::getChainTypeCode,dto.getChainTypeCode())
                .eq(ChainTypeDO::getTenantId,dto.getTenantId())
                .eq(ChainTypeDO::getAppId,dto.getAppId()));
        if(CollectionUtil.isNotEmpty(list)){
            //不为空，还有可能是更新时自身的编码
            if(list.size()==1){
                ChainTypeDO chainTypeDO = list.get(0);
                //该code是本身，不属于重复
               if(chainTypeDO.getId().equals(dto.getId())){
                   return true;
               }
            }
            return false;
        }
        return true;
    }



}