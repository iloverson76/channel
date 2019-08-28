package com.deepexi.channel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.chain.*;

import java.util.List;

/**
 * <p>
 * 连锁表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
public interface ChainService {

    ChainDTO getChain(Long id);

    Boolean insert(ChainDTO chainDTO);

    Boolean update(ChainDTO chainDTO);

    Boolean delete(List<Long> ids);
}
