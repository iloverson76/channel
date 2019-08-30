package com.deepexi.channel.businness;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;

import java.util.List;

public interface ChainBusinessService {
    Long insertChain(ChainDTO chainDTO);
    ChainDTO getChain(Long id);

    Boolean deleteChain(List<Long> ids);

    Boolean updateChain(ChainDTO clone);

    List<ChainDTO> findPage(ChainQuery query);
}
