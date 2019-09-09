package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.AreaBusinessService;
import com.deepexi.channel.domain.area.*;
import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;
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
            return new AreaDTO();
        }

        AreaTypeDTO typeDTO=areaTypeService.getAreaTypeById(areaTypeId);

        if(null!=typeDTO){
            areaDTO.setAreaType(typeDTO);
        }

        return areaDTO;
    }

    //区域树构建
    @Override
    public List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query) {

        List<AreaDTO> areaDTOList=areaService.findPage(new AreaQuery());

        if(CollectionUtils.isEmpty(areaDTOList)){
            return Collections.emptyList();
        }

        //区域类型设置
        List<AreaTypeDTO> typeDTOS = areaTypeService.listAreaTypePage(new AreaTypeQuery());

        if(CollectionUtils.isNotEmpty(typeDTOS)){

            Map<Long,AreaTypeDTO> typeDTOMap=typeDTOS.stream().collect(Collectors.toMap(AreaTypeDTO::getId,c->c));

            areaDTOList.forEach(area->{

                AreaTypeDTO typeDTO = typeDTOMap.get(area.getAreaTypeId());

                if(typeDTO!=null){

                    area.setAreaType(typeDTO);
                }
            });
        }

        //树构建
        List<AreaTreeDTO> list = ObjectCloneUtils.convertList(areaDTOList, AreaTreeDTO.class, CloneDirection.OPPOSITE);

        List<AreaTreeDTO> result = new ArrayList<>();

        for (AreaTreeDTO vo1 : list) {

            if (vo1.getChildren() == null) {

                vo1.setChildren(new LinkedHashSet<>());

            }
            if (0 == vo1.getParentId()) {

                result.add(vo1);
            }
            for (AreaTreeDTO vo2 : list) {

                if (0L != vo2.getParentId() && vo2.getParentId().equals(vo1.getId())) {

                    if (vo1.getChildren() == null) {

                        vo1.setChildren(new LinkedHashSet<>());
                    }
                    vo1.getChildren().add(vo2);
                }
            }
        }
        return result;
    }
}