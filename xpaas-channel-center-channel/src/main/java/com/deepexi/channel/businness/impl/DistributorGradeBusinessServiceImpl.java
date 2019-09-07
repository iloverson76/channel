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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        DistributorGradeDTO gdto=distributorGradeService.getById(gradeId);

        long parentId=gdto.getParentId();

        if(0!=parentId){

            DistributorGradeDTO pdto= distributorGradeService.getById(parentId);

            if(null!=pdto){

                gdto.setParentGradeCode(pdto.getDistributorGradeCode());

                gdto.setParentGradeName(pdto.getDistributorGradeName());
            }
        }

        if(0!=systemId){

            //体系表
            DistributorGradeSystemDTO sdto=distributorGradeSystemService.detail(systemId);

            gdto.setSystem(sdto);
        }

        return gdto;
    }

    @Override
    public List<DistributorGradeDTO> findPage(DistributorGradeQuery query) {

        log.info("经销商等级列表分页查询");

        //等级表数据
        List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(query);

        if(CollectionUtil.isEmpty(gradeList)){
            return Collections.emptyList();
        }

        //父级信息
        List<Long> parentIdList=gradeList.stream().map(DistributorGradeDTO::getParentId).collect(Collectors.toList());

        if(CollectionUtil.isNotEmpty(parentIdList)){

            DistributorGradeQuery parentQuery=new DistributorGradeQuery();

            parentQuery.setIds(parentIdList);

            List<DistributorGradeDTO> parentGradeList = distributorGradeService.findPage(parentQuery);

            if(CollectionUtil.isNotEmpty(parentGradeList)){

                Map<Long, List<DistributorGradeDTO>> parentGradeMap =
                        parentGradeList.stream().collect(Collectors.groupingBy(DistributorGradeDTO::getId));

                // 根据id设置父级编码和名称
                gradeList.forEach(grade -> {

                    List<DistributorGradeDTO> gradeDTOS = parentGradeMap.get(grade.getParentId());

                    if (CollectionUtil.isEmpty(gradeDTOS)) {

                        grade.setParentGradeCode("");

                        grade.setParentGradeName("");

                    } else {

                        DistributorGradeDTO gdto = gradeDTOS.get(0);//id是主键,只有一条记录

                        grade.setParentGradeCode(gdto.getDistributorGradeCode() == null ? "" : gdto.getDistributorGradeCode());

                        grade.setParentGradeName(gdto.getDistributorGradeName()==null? "": gdto.getDistributorGradeName());
                    }
                });
            }
        }

        //体系表数据
        List<Long> systemIdList=gradeList.stream().map(DistributorGradeDTO::getGradeSystemId).
                collect(Collectors.toList());

        DistributorGradeSystemQuery systemQry=new DistributorGradeSystemQuery();

        systemQry.setIds(systemIdList);

        List<DistributorGradeSystemDTO> systemList= distributorGradeSystemService.findPage(systemQry);

        Map<Long, List<DistributorGradeSystemDTO>> systemMap =

                systemList.stream().collect(Collectors.groupingBy(DistributorGradeSystemDTO::getId));

        gradeList.forEach(grade -> {

            long systemId=grade.getGradeSystemId();

            if(0!=systemId){

                // 根据id对应设置体系编码
                List<DistributorGradeSystemDTO> systemDTOS = systemMap.get(systemId);

                if (CollectionUtil.isNotEmpty(systemDTOS)) {

                    DistributorGradeSystemDTO sdto=systemDTOS.get(0);

                    grade.setSystem(sdto);
                }
            }
        });

        return gradeList;
    }

    @Override
    public List<DistributorGradeDTO> findParentNodesForCreat(long systemId) {//

        if(0==systemId){
            return Collections.emptyList();
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

    @Override
    public List<DistributorGradeSystemDTO> findAllGradesBySystem(long pk) {
        return null;
    }
}