package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.AreaBusinessService;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArearBusinessServiceImpl implements AreaBusinessService {

    @Autowired
    AreaService areaService;

    @Autowired
    AreaTypeService areaTypeService;

    @Transient
    @Override
    public long create(AreaDTO dto) {

        long result= areaService.create(dto);

        return result;
    }

    @Override
    public List<AreaDTO> findPage(AreaQuery query) {

        if(null==query){
            return Collections.emptyList();
        }

         List<AreaDTO> areaDTOList=areaService.findPage(query);

        //区域类型信息
        List<Long> areaTyeIdList=areaDTOList.stream().map(AreaDTO::getAreaTypeId).collect(Collectors.toList());

        if(CollectionUtils.isNotEmpty(areaTyeIdList)){

            List<AreaTypeDTO> AreaTypeList=areaTypeService.listAreaTypeByIds(areaTyeIdList);

            if(CollectionUtils.isNotEmpty(AreaTypeList)){

                Map<Long, List<AreaTypeDTO>> areaTypeMap =
                        AreaTypeList.stream().collect(Collectors.groupingBy(AreaTypeDTO::getId));

                areaDTOList.forEach(area->{

                    List<AreaTypeDTO> areaTypes=areaTypeMap.get(area.getAreaTypeId());

                    if (CollectionUtil.isNotEmpty(areaTypes)) {

                        AreaTypeDTO type = areaTypes.get(0);//id是主键,只有一条记录

                        area.setAreaType(type);
                    }
                });
            }
        }
        return areaDTOList;
    }

    @Override
    public AreaDTO detail(Long pk, Long areaTypeId) {

        AreaDTO areaDTO=areaService.getAreaById(pk);

        if(null==areaDTO){
            return null;
        }

        AreaTypeDTO typeDTO=areaTypeService.getAreaTypeById(areaTypeId);

        if(null!=typeDTO){
            areaDTO.setAreaType(typeDTO);
        }

        return areaDTO;
    }
}