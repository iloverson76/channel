package com.deepexi.channel.businness;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainDetailDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.chain.ChainTreeDTO;

import java.util.List;

public interface ChainBusinessService {
    Long insertChain(ChainDetailDTO chainDetailDTO);
    ChainDetailDTO getChain(Long id);

    Boolean deleteChain(List<Long> ids);

    Boolean deleteVerification(List<Long> ids);


    Boolean updateChain(ChainDetailDTO dto);

    List<ChainDetailDTO> findPage(ChainQuery query);

    Boolean saveTree(List<ChainTreeDTO> dtos);

    List<ChainTreeDTO> getTree();

    List<ChainTreeDTO> getChildren(Long id);

    Boolean updateTreeNode(ChainDTO chainDTO);


    Boolean deleteTreeNode(Long id);

    Boolean addTreeNode(ChainDTO chainDTO);

    List<ChainDTO> getLegalParentChainByChainId(Long chainTypeId);

    boolean isChangeChainTypeLegal(ChainDetailDTO dto);
}
