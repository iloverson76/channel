package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.ChainDO;
import com.deepexi.channel.domain.ChainQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
public interface ChainDAO extends IService<ChainDO> {

    List<ChainDO> findList(ChainQuery query);

    Integer getChainCountByTypeIds(List<Long> typeIds);

    List<ChainDO> findParentList(List<Long> ids);

    boolean updatePathAndParentIdBatch(List<ChainDO> chainDOS);

    List<ChainDO> getChainTreeNode();

    Integer resetTree(ChainDO chainDO);

    Boolean updatePathAndParentId(ChainDO chainDO);
}
