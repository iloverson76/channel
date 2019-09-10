package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.channel.dao.ChainDAO;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainDetailDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.ChainService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChainServiceImpl implements ChainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private ChainDAO chainDAO;

    @Override
    public List<ChainDTO> findPage(ChainQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<ChainDO> chainDOS =  chainDAO.findList(query);
        if(CollectionUtil.isEmpty(chainDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(chainDOS, ChainDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public ChainDTO detail(Long id) {
        ChainDO chainDO = chainDAO.getById(id);
        if(chainDO == null){
            return null;
        }
        return chainDO.clone(ChainDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean update(ChainDTO dto) {
        if (dto.getId() == null || dto.getId() == 0L) {
            return false;
        }
        ChainDO chainDO = dto.clone(ChainDO.class);
        boolean result = chainDAO.updateById(chainDO);
        return result;
    }
//
    @Override
    public Long create(ChainDTO dto) {
        if(dto == null){
            return 0L;
        }
        ChainDO chainDO = dto.clone(ChainDO.class);
        //新增连锁基本信息
        boolean result = chainDAO.save(chainDO);

        return chainDO.getId();
    }

    @Override
    public Boolean delete(List<Long> ids) {
        return chainDAO.removeByIds(ids);
    }

    /**
     * @MethodName: isCodeUnique
     * @Description: 编码是否唯一，更新时会排除本身
     * @Param: [dto]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/10
    **/
    @Override
    public boolean isCodeUnique(ChainDTO dto) {
        List<ChainDO> list = chainDAO.list(new QueryWrapper<ChainDO>().lambda()
                .eq(ChainDO::getChainCode,dto.getChainCode()));
        if(CollectionUtil.isNotEmpty(list)){
            //不为空，还有可能是更新时自身的编码
            if(list.size()==1){
                ChainDO chainDO = list.get(0);
                //该code是本身，不属于重复
                if(chainDO.getId().equals(dto.getId())){
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * 判断名称是否重复
     * @param dto
     * @return
     */
    @Override
    public boolean isNameUnique(ChainDetailDTO dto) {
        return false;
    }


    @Override
    public boolean haveChildren(List<Long> ids) {
        //获得所有子节点
        List<ChainDO> chainDOS = chainDAO.findParentList(ids);
        //没有子节点
        if(CollectionUtil.isEmpty(chainDOS)){
            return false;
        }
        //判断子节点是否也被删除，如果子节点不被删除，则拒绝删除
        for(ChainDO a : chainDOS){
            if(!ids.contains(a.getId())){
                return true;
            }
        }
        //父节点跟子节点一同删除, 允许删除
        return false;
    }

    /**
     * @MethodName: updatePathAndParentIdBatch
     * @Description: 批量更新path 和parentId，用于连琐树形结构用
     * @Param: [chainDTOS]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/10
    **/
    @Override
    public boolean updatePathAndParentIdBatch(List<ChainDTO> chainDTOS) {
        if(CollectionUtil.isEmpty(chainDTOS)){
            return false;
        }
        List<ChainDO> chainDOS = ObjectCloneUtils.convertList(chainDTOS, ChainDO.class);
        return chainDAO.updatePathAndParentIdBatch(chainDOS);
    }

    /**
     * @MethodName: getChainTreeNode
     * @Description: 获取展示三层的整棵树节点
     * @Param: []
     * @Return: java.util.List<com.deepexi.channel.domain.chain.ChainDTO>
     * @Author: mumu
     * @Date: 2019/9/10
    **/
    @Override
    public List<ChainDTO> getChainTreeNode() {
        List<ChainDO> chainDOS = chainDAO.getChainTreeNode();
        if(CollectionUtil.isEmpty(chainDOS)){
            return null;
        }
        List<ChainDTO> chainDTOS = ObjectCloneUtils.convertList(chainDOS, ChainDTO.class, CloneDirection.OPPOSITE);
        return chainDTOS;
    }

    /**
     * @MethodName: resetTree
     * @Description: 重置整一棵树
     * @Param: []
     * @Return: java.lang.Boolean
     * @Author: mumu
     * @Date: 2019/9/10
    **/
    @Override
    public Boolean resetTree() {
        ChainDTO dto = new ChainDTO();
        dto.setAppId(appRuntimeEnv.getAppId());
        dto.setTenantId(appRuntimeEnv.getTenantId());
        return chainDAO.resetTree(dto.clone(ChainDO.class)) > 0 ? true:false;
    }
}