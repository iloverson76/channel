package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.ForceDeleteEnum;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
    public Long create(DistributorGradeSystemDTO dto) {

        validateGradeSystemCode(dto.getGradeSystemCode());

        validateGradeSystemName(dto.getGradeSystemName());

        return distributorGradeSystemService.create(dto);
    }

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
        Map<Long, DistributorDTO> parentDistributorMap =  parentDistributorDTO.stream().collect(Collectors.toMap(DistributorDTO::getId, d->d));

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

    @Override
    public boolean deleteBatchByIds(List<Long> systemIdList,Integer forceDelete) {

        if(CollectionUtils.isEmpty(systemIdList)){
            return false;
        }

        ForceDeleteEnum.validateIllegalForceDeleteFlag(forceDelete);

        if(forceDelete== ForceDeleteEnum.NO.getCode()){

            //已挂载等级的不能删除
            validateHasGrades(systemIdList);
        }
        //删除体系
        return distributorGradeSystemService.delete(systemIdList);
    }

    @Override
    public void validateHasGrades(List<Long> systemIdList){
        DistributorGradeSystemQuery systemQuery=new DistributorGradeSystemQuery();

        systemQuery.setIds(systemIdList);

        List<DistributorGradeSystemDTO> systemList= distributorGradeSystemService.findPage(systemQuery);

        systemList.forEach(system->{

            Long systemId=system.getId();

            DistributorGradeQuery gradeQuery = new DistributorGradeQuery();

            gradeQuery.setSystemId(systemId);

            List<DistributorGradeDTO> gradeList = distributorGradeService.findPage(gradeQuery);

            if(CollectionUtil.isNotEmpty(gradeList)){

                throw new ApplicationException("["+system.getGradeSystemName()+"]已挂载已等级,不能删除!请解除所有关联后再操作");
            }
        });
    }

    @Override
    public void  validateGradeSystemCode(String systemCode) {

        log.info("经销商体系编码重复校验");

        List<String> codeList=distributorGradeSystemService.listGradeSystemCode();

        if(CollectionUtils.isNotEmpty(codeList)){

            codeList.forEach(code->{

                if(code.equals(systemCode)){

                    throw new ApplicationException(ResultEnum.DISTRIBUTOR_SYSTEM_CODE_DUPLICATED);
                }
            });
        }
    }

    @Override
    public void validateGradeSystemName(String systemName) {

        log.info("经销商体系中文重复校验");

        List<String> nameList=distributorGradeSystemService.listGradeSystemName();

        if(CollectionUtils.isNotEmpty(nameList)){

            nameList.forEach(name->{

                if(name.equals(systemName)){

                    throw new ApplicationException(ResultEnum.DISTRIBUTOR_SYSTEM_NAME_DUPLICATED);
                }
            });
        }
    }

    @Override
    public void validateGradeSystemNameEn(String systemNameEn) {

        log.info("经销商体系中文重复校验");

        List<String> nameEnList=distributorGradeSystemService.listGradeSystemNameEn();

        if(CollectionUtils.isNotEmpty(nameEnList)){

            nameEnList.forEach(nameEn->{

                if(nameEn.equals(systemNameEn)){

                    throw new ApplicationException(ResultEnum.DISTRIBUTOR_SYSTEM_NAME_DUPLICATED);
                }
            });
        }
    }
}