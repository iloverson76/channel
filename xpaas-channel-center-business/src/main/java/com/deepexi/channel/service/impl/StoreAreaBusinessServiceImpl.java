package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.service.AreaBusinessService;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.StoreAreaBusinessService;
import com.deepexi.channel.service.StoreAreaService;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    AreaBusinessService areaBusinessService;

    @Override
    public Boolean saveStoreAreaRelation(StoreDetailDTO dto) {
        List<AreaBusiDTO> areaDTOS = dto.getAreaDTOS();
        List<StoreAreaDTO> list = new LinkedList<>();
        areaDTOS.forEach(a->{
            StoreAreaDTO storeAreaDTO = StoreAreaDTO.builder().storeId(dto.getId()).areaId(a.getId()).build();
            list.add(storeAreaDTO);
        });
        return storeAreaService.saveBatch(list);
    }

    @Override
    public Boolean updateStoreAreaRelation(StoreDetailDTO dto) {
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
    public List<AreaBusiDTO> getStoreAreaByStoreId(Long storeId) {
        //获取关联信息
        StoreAreaQuery storeAreaQuery = StoreAreaQuery.builder().storeId(storeId).build();
        List<StoreAreaDTO> storeAreaDTOS = storeAreaService.findList(storeAreaQuery);
        if(CollectionUtil.isEmpty(storeAreaDTOS)){
            return null;
        }
        List<Long> ids = storeAreaDTOS.stream().map(StoreAreaDTO::getAreaId).collect(Collectors.toList());
        //获取区域
        AreaQuery areaQuery = new AreaQuery();
        areaQuery.setIds(ids);
        List<AreaBusiDTO> areaBusiDTOList = areaBusinessService.findPage(areaQuery);
        if(CollectionUtil.isEmpty(areaBusiDTOList)){
            return null;
        }
        return areaBusiDTOList;
    }
}