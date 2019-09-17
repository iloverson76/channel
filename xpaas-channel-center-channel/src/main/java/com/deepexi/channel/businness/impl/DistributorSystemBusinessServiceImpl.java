package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.businness.DistributorSystemBusinessService;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.domain.store.StoreDistributorDTO;
import com.deepexi.channel.service.DistributorGradeRelationService;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DistributorSystemBusinessServiceImpl implements DistributorSystemBusinessService {

    @Autowired
    private DistributorGradeService distributorGradeService;

    @Autowired
    private DistributorGradeSystemService distributorGradeSystemService;

    @Autowired
    private DistributorGradeRelationService distributorGradeRelationService;

    @Autowired
    private DistributorService distributorService;

    @Override
    public List<DistributorGradeSystemDTO> findPage(DistributorGradeSystemQuery query) {

        List<DistributorGradeSystemDTO> systemList=distributorGradeSystemService.findPage(query);

        List<DistributorGradeDTO> gradeList=distributorGradeService.findPage(new DistributorGradeQuery());

        systemList.forEach(system->{

            long systemId=system.getId();

            List<DistributorGradeDTO> gradeDTOS= new ArrayList<>();

            if(CollectionUtil.isNotEmpty(gradeList)){

                gradeList.forEach(grade->{

                    if(grade.getGradeSystemId()==systemId){

                        gradeDTOS.add(grade);

                        system.setGrades(gradeDTOS);
                    }
                });
            }
        });

        return systemList;
    }

    @Override
    public DistributorGradeSystemDTO detail(Long pk) {

        DistributorGradeSystemDTO system=distributorGradeSystemService.detail(pk);

        if(null==system){
            return new DistributorGradeSystemDTO();
        }

        List<DistributorGradeDTO> grades=distributorGradeService.findAllBySystem(pk);

        if(CollectionUtil.isNotEmpty(grades)){

            system.setGrades(grades);
        }

        return system;
    }

    /**
     * 根据经销商id获取所属所有等级体系
     * @param distributorId
     * @return
     */
    @Override
    public List<StoreDistributorDTO> getDistributorGradeSystemByDistributorId(long distributorId) {
        List<Long> ids = new LinkedList<>();
        ids.add(distributorId);
        List<DistributorGradeRelationDTO> list = distributorGradeRelationService.findAllByDistributorIds(ids);
        if(CollectionUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        List<StoreDistributorDTO> result = new LinkedList<>();
        //查询进销商等级体系
        List<Long> systemIds = list.stream().map(DistributorGradeRelationDTO::getSystemId).collect(Collectors.toList());
        DistributorGradeSystemQuery query = new DistributorGradeSystemQuery();
        query.setIds(systemIds);
        List<DistributorGradeSystemDTO> distributorGradeSystemDTOS = distributorGradeSystemService.findPage(query);
        Map<Long,DistributorGradeSystemDTO> distributorGradeSystemDTOMap = distributorGradeSystemDTOS.stream().collect(Collectors.toMap(DistributorGradeSystemDTO::getId,d->d));
        if(CollectionUtil.isEmpty(distributorGradeSystemDTOS)){
            return Collections.emptyList();
        }

        //查询父级经销商
        Set<Long> parentDistributorIds = list.stream().map(DistributorGradeRelationDTO::getParentId).collect(Collectors.toSet());
        DistributorQuery distributorQuery = new DistributorQuery();
        distributorQuery.setIds(new LinkedList<>(parentDistributorIds));
        List<DistributorDTO> parentDistributorDTO = distributorService.findPage(distributorQuery);
        Map<Long,DistributorDTO> parentDistributorMap =  parentDistributorDTO.stream().collect(Collectors.toMap(DistributorDTO::getId,d->d));

        //拼接结果
        for(DistributorGradeRelationDTO d : list){
            StoreDistributorDTO dto = new StoreDistributorDTO();
            //拼接等级体系信息
            DistributorGradeSystemDTO gradeSystemDTO = distributorGradeSystemDTOMap.get(d.getSystemId());
            if(gradeSystemDTO != null){
                dto.setGradeSystemName(gradeSystemDTO.getGradeSystemName());
                dto.setGradeSystemCode(gradeSystemDTO.getGradeSystemCode());
                dto.setGradeSystemId(gradeSystemDTO.getId());
            }
           //拼接父级经销商信息
            DistributorDTO parent = parentDistributorMap.get(d.getParentId());
            if(parent != null){
                dto.setParentName(parent.getDistributorName());
                dto.setParentCode(parent.getDistributorCode());
                dto.setParentId(parent.getId());
            }else{
                dto.setParentName("");
                dto.setParentCode("");
            }
            result.add(dto);
        }
        return result;
    }
}