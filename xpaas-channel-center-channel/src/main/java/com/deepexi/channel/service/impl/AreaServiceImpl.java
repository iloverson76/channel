package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IAreaDAO;
import com.deepexi.channel.domain.AreaDO;
import com.deepexi.channel.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    IAreaDAO iAreaDAO;

    @Override
    public boolean getById(Long id) {
        iAreaDAO.getById(id);
        return Boolean.TRUE;
    }
}
