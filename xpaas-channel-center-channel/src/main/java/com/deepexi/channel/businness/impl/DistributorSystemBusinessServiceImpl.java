package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.businness.DistributorSystemBusinessService;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.service.DistributorGradeRelationService;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.util.CollectionUtil;
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
    public List<DistributorGradeSystemDTO> getDistributorGradeSystemByDistributorId(long distributorId) {
        List<Long> ids = new LinkedList<>();
        ids.add(distributorId);
        List<DistributorGradeRelationDTO> list = distributorGradeRelationService.findAllByDistributorIds(ids);
        if(CollectionUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        List<Long> systemIds = list.stream().map(DistributorGradeRelationDTO::getSystemId).collect(Collectors.toList());
        DistributorGradeSystemQuery query = new DistributorGradeSystemQuery();
        query.setIds(systemIds);
        List<DistributorGradeSystemDTO> result = distributorGradeSystemService.findPage(query);
        return result;
    }
}