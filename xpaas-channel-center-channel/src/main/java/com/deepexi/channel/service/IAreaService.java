package com.deepexi.channel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.AreaDTO;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
public interface IAreaService{

    boolean getById(Long id);
}
