package com.deepexi.channel.service;

import com.deepexi.channel.domain.chain.ChainDTO;

public interface ChainBusiness {
    Boolean insertChain(ChainDTO chainDTO);
    ChainDTO getChain(Long id);
}
