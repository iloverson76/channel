package com.deepexi.channel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;

import java.util.List;

/**
 * <p>
 * 连锁类型表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
public interface IChainTypeService{

    ChainTypeDTO getChainType(Long id);

    List<ChainTypeDTO> listChainType(ChainTypeQuery query);
}
