package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.deepexi.channel.dao.ChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.enums.LimitedEnum;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 连琐类型Service
 * @author mumu
 */
@Slf4j
@Service
public class ChainTypeServiceImpl implements ChainTypeService {

    AppRuntimeEnv appRuntimeEnv= AppRuntimeEnv.getInstance();
    @Autowired
    private ChainTypeDAO chainTypeDAO;

    @Override
    public List<ChainTypeDTO> findPage(ChainTypeQuery query) {
        if (query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<ChainTypeDO> chainTypeDOS = chainTypeDAO.findList(query);
        List<ChainTypeDTO> chainTypeDTOS = ObjectCloneUtils.convertList(chainTypeDOS, ChainTypeDTO.class, CloneDirection.OPPOSITE);
        return chainTypeDTOS;
    }

    @Override
    public List<ChainTypeDTO> findAll(ChainTypeQuery query) {
        List<ChainTypeDO> list = chainTypeDAO.findList(query);
        return ObjectCloneUtils.convertList(list, ChainTypeDTO.class, CloneDirection.OPPOSITE);
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
    public Boolean update(ChainTypeDTO dto) {
        boolean result = chainTypeDAO.updateById(dto.clone(ChainTypeDO.class));
        return result;
    }

    @Override
    public Long create(ChainTypeDTO dto) {
        //插入
        ChainTypeDO chainTypeDO = dto.clone(ChainTypeDO.class);
        boolean result = chainTypeDAO.save(chainTypeDO);
        return chainTypeDO.getId();
    }


    @Override
    public Boolean delete(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return false;
        }
        return chainTypeDAO.removeByIds(ids);
    }

    /**
     * @MethodName: haveChilder
     * @Description: 是否具有儿子节点, 并且不在ids中
     * @Param: [ids]
     * @Return: java.lang.Boolean
     * @Author: mumu
     * @Date: 2019/8/30
     **/
    @Override
    public Boolean haveChildren(List<Long> ids) {
        //获得所有子节点
        ChainTypeQuery query = ChainTypeQuery.builder().ids(ids).build();
        query.setPage(-1);
        List<ChainTypeDO> chainTypeDOS = chainTypeDAO.findList(query);
        //没有子节点
        if (CollectionUtil.isEmpty(chainTypeDOS)) {
            return false;
        }
        //判断子节点是否也被删除，如果子节点不被删除，则拒绝删除
        for (ChainTypeDO a : chainTypeDOS) {
            if (!ids.contains(a.getId())) {
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
    public boolean isCodeUnique(ChainTypeDTO dto) {
        ChainTypeQuery query = ChainTypeQuery.builder().chainTypeAccuracyCode(dto.getChainTypeCode()).build();
        return this.isUnique(query, dto);
    }

    /**
     * @MethodName: isNameUnique
     * @Description: 判断连锁类型是否重复
     * @Param: [code]
     * @Return: boolean 名称唯一, true 名称唯一 ， false 名称不唯一
     * @Author: mumu
     * @Date: 2019/8/30
     **/
    @Override
    public boolean isNameUnique(ChainTypeDTO dto) {
        ChainTypeQuery query = ChainTypeQuery.builder().chainTypeAccuracyName(dto.getChainTypeName()).build();
        return this.isUnique(query, dto);
    }
    /**
     * @MethodName: isUnique
     * @Description: 判断某个属性是否唯一，排除自身干扰
     * @Param: [query, dto] query是查询出某个属性重复的列表，dto是来判断是否唯一的dto
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/10
    **/
    private boolean isUnique(ChainTypeQuery query, ChainTypeDTO dto){
        List<ChainTypeDO> list = chainTypeDAO.findList(query);
        if (CollectionUtil.isNotEmpty(list)) {
            //不为空，还有可能是更新时自身的编码
            if (list.size() == 1) {
                ChainTypeDO chainTypeDO = list.get(0);
                //该code是本身，不属于重复
                if (chainTypeDO.getId().equals(dto.getId())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * @MethodName: isParentLegal
     * @Description: 判断限制上级、父级id是否合法
     * @Param: [dto]
     * @Return: boolean 合法可以设置  true   不合法  false
     * @Author: mumu
     * @Date: 2019/9/10
    **/
    @Override
    public boolean isParentLegal(ChainTypeDTO dto) {
        //不限制上级
        if(dto.getLimitParent() == 0L){
            return true;
        }
        //限制上级，就有父级节点，判断设置父级是否合法
        ChainTypeQuery query = ChainTypeQuery.builder().parentId(dto.getParentId()).build();
        query.setPage(-1);
        List<ChainTypeDTO> chainTypeDTOS = this.findAll(query);
        if(CollectionUtil.isEmpty(chainTypeDTOS)){
            return true;
        }else{
            //父级节点被其他节点设置了父亲，只能1:1, 但需要排除节点本身
            for (ChainTypeDTO c : chainTypeDTOS){
                if(!c.getId().equals(dto.getId())){
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public List<ChainTypeDTO> findByChainIdNotInAll(List<Long> chainIdList) {
        List<ChainTypeDO> chainTypeDO = chainTypeDAO.findByChainIdNotInAll(chainIdList);
        List<ChainTypeDTO> chainTypeDTOList = ObjectCloneUtils.convertList(chainTypeDO, ChainTypeDTO.class, CloneDirection.OPPOSITE);
        return chainTypeDTOList;
    }

    @Override
    public Boolean updateBatch(List<ChainTypeDTO> list) {
        if(CollectionUtil.isEmpty(list)){
            return false;
        }
        List<ChainTypeDO> chainTypeDOS = ObjectCloneUtils.convertList(list, ChainTypeDO.class);
        return chainTypeDAO.updateBatchById(chainTypeDOS);
    }

    
    @Override
    public List<ChainTypeDTO> listParentNodesForCreate() {

        //没有被限制分类的节点
        List<ChainTypeDO> doList=chainTypeDAO.listNotLimitedNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        //首次创建处理空值
        if(CollectionUtils.isNotEmpty(doList)){

            List<ChainTypeDTO> dtoList=ObjectCloneUtils.convertList(doList, ChainTypeDTO.class, CloneDirection.OPPOSITE);

            return dtoList;
        }
        return Collections.emptyList();
    }
    /**
     * 更新节点时的上级列表
     *
     * 上级与下级只能1:1
     *
     */
    @Override
    public List<ChainTypeDTO> listParentNodesForUpdate(Long id) {
        //没有被限制分类的节点
        List<ChainTypeDO> unLimitedNodes=chainTypeDAO.listNotLimitedNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        if(org.apache.commons.collections.CollectionUtils.isEmpty(unLimitedNodes)){
            return Collections.emptyList();
        }
        //如果自己原来的上级被限制了,也要选上
        ChainTypeDTO node=detail(id);

        long parentId=node.getParentId();

        boolean limited= LimitedEnum.LIMITED.getCode().equals(node.getLimitParent());

        if(0!=parentId&&limited){

            ChainTypeDTO parentDTO=detail(parentId);

            unLimitedNodes.add(parentDTO.clone(ChainTypeDO.class,CloneDirection.FORWARD));
        }

        //不能选自己和自己的所有子节点
        ChainTypeDO self=chainTypeDAO.getById(id);

        List<ChainTypeDO>  childNodes=chainTypeDAO.listChildNodes(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId(),id+"/");

        List<ChainTypeDO> removeList=new ArrayList<>();

        unLimitedNodes.forEach(u->{

            if(u.getId().equals(self.getId())){

                removeList.add(u);
            }

            if(org.apache.commons.collections.CollectionUtils.isNotEmpty(childNodes)){

                childNodes.forEach(c->{

                    if(c.getId().equals(u.getId())){

                        removeList.add(u);
                    }
                });
            }
        });

        unLimitedNodes.removeAll(removeList);

        return ObjectCloneUtils.convertList(unLimitedNodes,ChainTypeDTO.class);
    }
}