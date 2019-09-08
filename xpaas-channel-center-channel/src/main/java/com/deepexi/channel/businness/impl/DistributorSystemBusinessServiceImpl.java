package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.businness.DistributorSystemBusinessService;
import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemQuery;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.util.CollectionUtil;
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
}