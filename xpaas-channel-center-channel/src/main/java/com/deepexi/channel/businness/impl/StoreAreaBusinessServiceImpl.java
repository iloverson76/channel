package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreAreaBusinessService;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.store.StoreAreaDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.StoreAreaService;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:33
 */
@Service
public class StoreAreaBusinessServiceImpl implements StoreAreaBusinessService {

    @Autowired
    StoreAreaService storeAreaService;
    @Autowired
    AreaService areaService;

    @Override
    public Long saveStoreAreaRelation(StoreDetailDTO dto) {
        AreaDTO areaDTO = dto.getAreaDTO();
        StoreAreaDTO storeAreaDTO = StoreAreaDTO.builder().storeId(dto.getId()).areaId(areaDTO.getId()).build();
        return storeAreaService.save(storeAreaDTO);
    }

    @Override
    public Long updateStoreAreaRelation(StoreDetailDTO dto) {
        //根据门店id删除门店区域关联
        Boolean result = storeAreaService.removeByStoreId(dto.getId());
        //保存最新的门店区域关联
        return this.saveStoreAreaRelation(dto);
    }

    @Override
    public Boolean deleteStoreAreaRelation(List<Long> ids) {
        return storeAreaService.removeByStoreIds(ids);
    }

    @Override
    public AreaDTO getStoreAreaByStoreId(Long storeId) {
        //获取关联信息
        StoreAreaDTO storeAreaDTO = storeAreaService.getStoreAreaByStoreId(storeId);
        if(storeAreaDTO == null){
            return null;
        }
        //获取区域
        AreaQuery areaQuery = new AreaQuery();
        areaQuery.setId(storeAreaDTO.getAreaId());
        List<AreaDTO> areaDTOList = areaService.findPage(areaQuery);
        if(CollectionUtil.isEmpty(areaDTOList)){
            return null;
        }
        AreaDTO areaDTO = areaDTOList.get(0);
        return areaDTO;
    }
}