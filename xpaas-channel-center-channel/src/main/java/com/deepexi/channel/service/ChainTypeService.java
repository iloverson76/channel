package com.deepexi.channel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * <p>
 * 连锁类型表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
public interface ChainTypeService {

    ChainTypeDTO getChainType(Long id);

    List<ChainTypeDTO> listChainType(ChainTypeQuery query);

    /**
     * 不分页，不查询父级节点信息
     * @return
     */
    List<ChainTypeDTO> listChainType();

    Boolean insert(ChainTypeDTO dto);

    Boolean update(ChainTypeDTO dto);

    Boolean delete(List<Long> ids);
}
