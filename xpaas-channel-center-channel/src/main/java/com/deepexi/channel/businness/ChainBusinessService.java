package com.deepexi.channel.businness;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainDetailDTO;
import com.deepexi.channel.domain.chain.ChainQuery;

import java.util.List;

public interface ChainBusinessService {
    Long insertChain(ChainDetailDTO chainDetailDTO);
    ChainDetailDTO getChain(Long id);

    Boolean deleteChain(List<Long> ids);

    Boolean updateChain(ChainDetailDTO dto);

    List<ChainDetailDTO> findPage(ChainQuery query);
}
