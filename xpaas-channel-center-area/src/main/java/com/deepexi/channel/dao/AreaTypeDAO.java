package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.AreaTypeDO;
import com.deepexi.channel.domain.AreaTypeDTO;
import com.deepexi.channel.domain.AreaTypeQuery;

import java.util.List;

public interface AreaTypeDAO extends  IService<AreaTypeDO> {

    List<AreaTypeDO> listAreaTypePage(AreaTypeQuery query);

    List<String> listAreaTypeCode();

    List<AreaTypeDO> listChildNodes(String idPath);

    List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList);

    List<AreaTypeDO> listLinkedAreas(long pk);

    List<AreaTypeDO> findByAreaIdNotInLinkIdAll(List<Long> linkIdList);

    boolean update(AreaTypeDO tdo);
}
