package com.deepexi.channel.businness;

import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;

import java.util.List;

public interface ChainTypeBusinessService {

    boolean deleteChainType(List<Long> ids);

    boolean haveChainRelation(List<Long> ids);

    List<ChainTypeDTO> findPage(ChainTypeQuery query);

    List<ChainTypeDTO> getLegalParentChainType(Long id);

    Long create(ChainTypeDTO dto);

    List<ChainTypeDTO> getListChainType(List<Long> ids);

    Boolean update(ChainTypeDTO dto);
}
