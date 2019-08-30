package com.deepexi.channel.businness;

import com.deepexi.channel.domain.chain.ChainDTO;

import java.util.List;

public interface ChainBusinessService {
    Boolean insertChain(ChainDTO chainDTO);
    ChainDTO getChain(Long id);

    Boolean deleteChain(List<Long> ids);

    Boolean updateChain(ChainDTO clone);
}
