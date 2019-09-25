package com.deepexi.channel.service;


import com.deepexi.channel.domain.ChainDTO;
import com.deepexi.channel.domain.ChainDetailDTO;
import com.deepexi.channel.domain.ChainQuery;
import com.deepexi.channel.domain.ChainTreeDTO;

import java.util.List;

public interface ChainBusinessService {
    Long insertChain(ChainDetailDTO chainDetailDTO);

    ChainDetailDTO getChain(Long id);

    Boolean deleteChain(List<Long> ids, Integer forceDelete);

    Boolean deleteVerification(List<Long> ids);


    Boolean updateChain(ChainDetailDTO dto);

    List<ChainDetailDTO> findPage(ChainQuery query);

    Boolean saveTree(List<ChainTreeDTO> dtos);

    List<ChainTreeDTO> getTree();

    List<ChainTreeDTO> getChildren(Long id);

    Boolean updateTreeNode(ChainDTO chainDTO);

    Boolean deleteTreeNodeByIds(List<Long> ids);

    Boolean deleteTreeNode(Long id);

    Boolean addTreeNode(ChainDTO chainDTO);

    List<ChainDTO> getLegalParentChainByChainId(Long chainTypeId);

    boolean isChangeChainTypeLegal(ChainDetailDTO dto);

}
