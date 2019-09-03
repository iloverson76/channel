package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DistributorGradeBusinessServiceImpl implements DistributorGradeBusinessService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DistributorGradeService distributorGradeService;

    @Autowired
    private DistributorGradeSystemService distributorGradeSystemService;


    @Override
    public DistributorGradeDTO detail(Long gradeId,Long systemId) {

        //等级表
        DistributorGradeDTO gdto=distributorGradeService.detail(gradeId).clone(DistributorGradeDTO.class,CloneDirection.OPPOSITE);

        DistributorGradeBusiDTO busiDTO=gdto.clone(DistributorGradeBusiDTO.class,CloneDirection.FORWARD);

        if(0!=systemId){

            //体系表
            DistributorGradeSystemDTO sdto=distributorGradeSystemService.detail(systemId);

            busiDTO.setSystem(sdto);
        }

        return gdto;
    }

    @Override
    public List<DistributorGradeBusiDTO> findPage(DistributorGradeQuery query) {

        //等级表数据
        List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(query);

        if(null==gradeList){
            return null;
        }

        List<Long> parentIdList=gradeList.stream().map(DistributorGradeDTO::getParentId).collect(Collectors.toList());

        DistributorGradeQuery parentQuery=new DistributorGradeQuery();

        parentQuery.setIds(parentIdList);

        List<DistributorGradeDTO> parentGradeList = distributorGradeService.findPage(parentQuery);

        Map<Long, List<DistributorGradeDTO>> parentGradeMap =
                parentGradeList.stream().collect(Collectors.groupingBy(DistributorGradeDTO::getId));

        List<DistributorGradeBusiDTO> gradePageList=new ArrayList<>();

        // 根据id设置父级编码和名称
        gradeList.forEach(m -> {

            //先复制相同属性
            DistributorGradeBusiDTO gradePageDTO=m.clone(DistributorGradeBusiDTO.class,CloneDirection.FORWARD);

            List<DistributorGradeDTO> gradeDTOS = parentGradeMap.get(m.getParentId());

            if (CollectionUtil.isEmpty(gradeDTOS)) {

                gradePageDTO.setParentGradeCode("");

                gradePageDTO.setParentGradeName("");

            } else {

                DistributorGradeDTO gdto = gradeDTOS.get(0);//id是主键,只有一条记录

                gradePageDTO.setParentGradeCode(gdto.getDistributorGradeCode() == null ? "" : gdto.getDistributorGradeCode());

                gradePageDTO.setParentGradeName(gdto.getDistributorGradeName()==null? "": gdto.getDistributorGradeName());
            }

            gradePageList.add(gradePageDTO);
        });

        //体系表数据
        List<Long> systemIdList=gradeList.stream().map(DistributorGradeDTO::getGradeSystemId).
                collect(Collectors.toList());

        DistributorGradeSystemQuery systemQry=new DistributorGradeSystemQuery();

        systemQry.setIds(systemIdList);

        List<DistributorGradeSystemDTO> systemList= distributorGradeSystemService.findPage(systemQry);

        Map<Long, List<DistributorGradeSystemDTO>> systemMap =

                systemList.stream().collect(Collectors.groupingBy(DistributorGradeSystemDTO::getId));

        gradePageList.forEach(page -> {

            long systemId=page.getGradeSystemId();

            if(0!=systemId){

                // 根据id对应设置体系编码
                List<DistributorGradeSystemDTO> systemDTOS = systemMap.get(systemId);

                if (CollectionUtil.isNotEmpty(systemDTOS)) {

                    DistributorGradeSystemDTO sdto=systemDTOS.get(0);

                    page.setSystem(sdto);
                }
            }
        });

        return gradePageList;
    }
}