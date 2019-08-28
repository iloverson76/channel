package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IAreaDAO;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.service.AreaService;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    IAreaDAO iAreaDAO;

    @Override
    public boolean saveArea(AreaDTO dto) {

        return iAreaDAO.save(dto.clone(AreaDO.class,CloneDirection.FORWARD));
    }


}
