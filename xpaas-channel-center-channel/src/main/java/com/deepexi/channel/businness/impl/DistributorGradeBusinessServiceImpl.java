package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.dao.DistributorGradeDAO;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DistributorGradeBusinessServiceImpl implements DistributorGradeBusinessService {

    @Autowired
    private DistributorGradeService distributorGradeService;

    @Autowired
    private DistributorGradeDAO distributorGradeDAO;

    @Autowired
    private DistributorGradeSystemService distributorGradeSystemService;


    @Override
    public DistributorGradeDTO detail(Long gradeId,Long systemId) {

        log.info("查看经销商等级详情");

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

        log.info("查找经销商等级列表");

        //等级表数据
        List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(query);

        if(null==gradeList){
            return null;
        }

        List<Long> parentIdList=gradeList.stream().map(DistributorGradeDTO::getParentId).collect(Collectors.toList());

        DistributorGradeQuery parentQuery=new DistributorGradeQuery();

        parentQuery.setIds(parentIdList);

        List<DistributorGradeDTO> parentGradeList = distributorGradeService.findPage(parentQuery);

        List<DistributorGradeBusiDTO> gradePageList=
                ObjectCloneUtils.convertList(gradeList,DistributorGradeBusiDTO.class,CloneDirection.FORWARD);

        if(CollectionUtil.isNotEmpty(parentGradeList)){

            Map<Long, List<DistributorGradeDTO>> parentGradeMap =
                    parentGradeList.stream().collect(Collectors.groupingBy(DistributorGradeDTO::getId));

            // 根据id设置父级编码和名称
            gradePageList.forEach(gradePageDTO -> {

                List<DistributorGradeDTO> gradeDTOS = parentGradeMap.get(gradePageDTO.getParentId());

                if (CollectionUtil.isEmpty(gradeDTOS)) {

                    gradePageDTO.setParentGradeCode("");

                    gradePageDTO.setParentGradeName("");

                } else {

                    DistributorGradeDTO gdto = gradeDTOS.get(0);//id是主键,只有一条记录

                    gradePageDTO.setParentGradeCode(gdto.getDistributorGradeCode() == null ? "" : gdto.getDistributorGradeCode());

                    gradePageDTO.setParentGradeName(gdto.getDistributorGradeName()==null? "": gdto.getDistributorGradeName());
                }
            });
        }

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

    @Override
    public List<DistributorGradeDTO> findParentNodesForCreat(long systemId) {

        if(0==systemId){
            return null;
        }

        //等级表数据
        DistributorGradeQuery query=new DistributorGradeQuery();

        List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(query);

        List<DistributorGradeDTO> resultList=new ArrayList<>();

        List<Long> parentIdList=gradeList.stream().map(DistributorGradeDTO::getParentId).collect(Collectors.toList());

        Map<Long, Long> parentMap =new HashMap<>();

        parentIdList.forEach(pid->{
            parentMap.put(pid,pid);
        });

        if(CollectionUtil.isNotEmpty(parentIdList)){

            gradeList.forEach(grade->{

                Long v=parentMap.get(grade.getId());

                if(null==v&&grade.getGradeSystemId()==systemId){//按体系查
                    resultList.add(grade);
                }
            });
        }

        return resultList;
    }
}