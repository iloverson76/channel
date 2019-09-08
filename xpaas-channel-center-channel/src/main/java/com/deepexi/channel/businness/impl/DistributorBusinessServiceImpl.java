package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorBusinessService;
import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.dao.DistributorGradeRelationDAO;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountQuery;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.enums.DistributorTypeEnum;
import com.deepexi.channel.service.*;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class DistributorBusinessServiceImpl implements DistributorBusinessService {

    @Autowired
    private DistributorService distributorService;

    @Autowired
    DistributorGradeRelationService distributorGradeRelationService;

    @Autowired
    DistributorGradeService distributorGradeService;

    @Autowired
    DistributorAreaRelationService distributorAreaRelationService;

    @Autowired
    private DistributorBankAccountRelationService distributorBankAccountRelationService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    AreaService areaService;


    @Transient
    @Override
    public long create(DistributorDTO distributor) {

        //经销商信息保存
        long distId=distributorService.create(distributor);

        String createdBy=distributor.getCreatedBy();
        Date createdTime=distributor.getCreatedTime();
        String updatedBy=distributor.getUpdatedBy();
        Date updatedTime=distributor.getUpdatedTime();

        //等级信息保存
        List<Long> gradeList= distributor.getGradeIds();

        List<DistributorGradeRelationDTO> dgrList=new ArrayList<>();

        gradeList.forEach(gid->{

            DistributorGradeRelationDTO dgr=new DistributorGradeRelationDTO();

            dgr.setDistributorId(distId);

            dgr.setDistributorGradeId(gid);

            dgr.setCreatedBy(createdBy);

            dgr.setCreatedTime(createdTime);

            dgr.setUpdatedBy(updatedBy);

            dgr.setUpdatedTime(updatedTime);

            dgrList.add(dgr);

        });

        distributorGradeRelationService.createBatch(dgrList);

        //区域信息保存
        long areaId=distributor.getAreaId();

        DistributorAreaRelationDTO adto=new DistributorAreaRelationDTO();

        adto.setAreaId(areaId);

        adto.setDistributorId(distId);

        adto.setCreatedBy(createdBy);

        adto.setCreatedTime(createdTime);

        adto.setUpdatedBy(updatedBy);

        adto.setUpdatedTime(updatedTime);

        distributorAreaRelationService.create(adto);

        //账号信息批量保存
        List<Long> accountIds=distributor.getBankAccountIds();

        List<DistributorBankAccountRelationDTO> barList=new ArrayList<>();

        accountIds.forEach(a->{

            DistributorBankAccountRelationDTO bar=new DistributorBankAccountRelationDTO();

            bar.setBankAccountId(a);

            bar.setDistributorId(distId);

            bar.setCreatedBy(createdBy);

            bar.setCreatedTime(createdTime);

            bar.setUpdatedBy(updatedBy);

            bar.setUpdatedTime(updatedTime);

            barList.add(bar);
        });

        distributorBankAccountRelationService.create(barList);

        return distId;
    }

    @Override
    public boolean delete(List<Long> butorIdList) {


        distributorService.deleteBatch(butorIdList);

        distributorGradeRelationService.deleteBatchByDistributorIds(butorIdList);

        distributorAreaRelationService.deleteBatchByDistributorIds(butorIdList);

        distributorBankAccountRelationService.deleteBatchByDistributorIds(butorIdList);

        return Boolean.TRUE;
    }

    @Override
    public List<DistributorDTO> findPage(DistributorQuery query) {

        List<DistributorDTO> dtoList=distributorService.findPage(query);

        if(CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }

        List<Map<String, String>> list = DistributorTypeEnum.getTypeList();

        dtoList.forEach(dto->{

            for (Map<String, String> map : list) {

                if (dto.getDistributorType() == Integer.valueOf(map.get("code"))) {

                    dto.setDistributorTypeDesc(map.get("msg"));
                }
            }

        });

       return dtoList;
    }

    @Override
    public boolean update(DistributorDTO dto) {

        long distributorId=dto.getId();

        List<Long> gradeIds = dto.getGradeIds();

        if(CollectionUtils.isNotEmpty(gradeIds)){

            distributorGradeRelationService.deleteByDistributorId(distributorId);

            List<DistributorGradeRelationDTO> dtoList=new ArrayList<>();
            gradeIds.forEach(gradeId->{

                DistributorGradeRelationDTO gradeRelationDTO = new DistributorGradeRelationDTO();

                gradeRelationDTO.setDistributorId(distributorId);

                gradeRelationDTO.setDistributorGradeId(gradeId);
            });
           distributorGradeRelationService.createBatch(dtoList);
        }

        AreaDTO areaDTO = dto.getArea();

//        if(CollectionUtils.isNotEmpty()){
//
//        }
//
//        List<BankAccountDTO> bankAccountDTOS = dto.getBankAccounts();
//
//        if(CollectionUtils.isNotEmpty()){
//
//        }

        distributorService.update(dto);

        return true;
    }

    @Override
    public DistributorDTO detail(Long distributorId) {

        DistributorDTO distributor = distributorService.getById(distributorId);

        List<DistributorGradeDTO> grades=getGradeInfo(distributorId);

        List<BankAccountDTO> bankAccounts=getBankAccountInfo(distributorId);

        AreaDTO area =getAreaInfo(distributorId);

        distributor.setArea(area);

        distributor.setGrades(grades);

        distributor.setBankAccounts(bankAccounts);

        return distributor;
    }


    @Override
    public AreaDTO getAreaInfo(Long distributorId){//要把上级的信息全部查出来--未完善

        DistributorAreaRelationDTO bar = distributorAreaRelationService.findOneByDistributorId(distributorId);

        if(null==bar){
            return new AreaDTO();
        }

        return areaService.getAreaById(bar.getAreaId());

    }

    @Override
    public List<DistributorGradeDTO> getGradeInfo(Long distributorId){

        List<Long> butorIds=new ArrayList<>(1);

        butorIds.add(distributorId);

        List<DistributorGradeRelationDTO> dgrDTOS=
        distributorGradeRelationService.findAllByDistributorIds(butorIds);

        List<Long> gradeIdList=new ArrayList<>();

        if(CollectionUtils.isEmpty(dgrDTOS)){
            return Collections.emptyList();
        }

        dgrDTOS.forEach(dgr->{
            gradeIdList.add(dgr.getDistributorGradeId());
        });

        DistributorGradeQuery query=new DistributorGradeQuery();

        query.setIds(gradeIdList);

        return distributorGradeService.findPage(query);
    }

    @Override
    public List<BankAccountDTO> getBankAccountInfo(Long distributorId){

        List<Long> butorIds=new ArrayList<>(1);

        butorIds.add(distributorId);

        List<DistributorBankAccountRelationDTO> barDTOS = distributorBankAccountRelationService.findAllByDistributorIds(butorIds);

        List<Long> accountIdList=new ArrayList<>();

        if(CollectionUtils.isEmpty(barDTOS)){
            return Collections.emptyList();
        }

        barDTOS.forEach(bar->{

            accountIdList.add(bar.getBankAccountId());
        });

        BankAccountQuery query=new BankAccountQuery();

        query.setIds(accountIdList);

        return bankAccountService.findList(query);
    }

}