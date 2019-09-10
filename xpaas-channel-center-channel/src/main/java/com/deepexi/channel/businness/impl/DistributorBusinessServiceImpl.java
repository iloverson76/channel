package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorBusinessService;
import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.dao.DistributorGradeRelationDAO;
import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountQuery;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.enums.DistributorTypeEnum;
import com.deepexi.channel.service.*;
import com.deepexi.util.StringUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.checkerframework.checker.units.qual.A;
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

        if(CollectionUtils.isNotEmpty(gradeList)){

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
        }

        //区域信息保存
        long areaId=distributor.getAreaId();

        if(areaId>0){
            DistributorAreaRelationDTO adto=new DistributorAreaRelationDTO();

            adto.setAreaId(areaId);

            adto.setDistributorId(distId);

            adto.setCreatedBy(createdBy);

            adto.setCreatedTime(createdTime);

            adto.setUpdatedBy(updatedBy);

            adto.setUpdatedTime(updatedTime);

            distributorAreaRelationService.create(adto);
        }

        //账号信息批量保存
        List<Long> accountIds=distributor.getBankAccountIds();

        if(CollectionUtils.isNotEmpty(accountIds)){
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

            distributorBankAccountRelationService.batchCreate(barList);
        }

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

        String createdBy=dto.getCreatedBy();
        Date createdTime=dto.getCreatedTime();
        String updatedBy=dto.getUpdatedBy();
        Date updatedTime=dto.getUpdatedTime();

        List<Long> distriburotIds=new ArrayList<>(1);

        distriburotIds.add(dto.getId());

        List<Long> gradeIds = dto.getGradeIds();

        if(CollectionUtils.isNotEmpty(gradeIds)){//先删除再新建

            distributorGradeRelationService.deleteByDistributorId(distributorId);

            List<DistributorGradeRelationDTO> dtoList=new ArrayList<>();
            gradeIds.forEach(gradeId->{

                DistributorGradeRelationDTO gradeRelationDTO = new DistributorGradeRelationDTO();

                gradeRelationDTO.setDistributorId(distributorId);

                gradeRelationDTO.setDistributorGradeId(gradeId);

                gradeRelationDTO.setCreatedBy(createdBy);

                gradeRelationDTO.setCreatedTime(createdTime);

                gradeRelationDTO.setUpdatedBy(updatedBy);

                gradeRelationDTO.setCreatedTime(updatedTime);

            });
           distributorGradeRelationService.createBatch(dtoList);
        }

        AreaDTO areaDTO = dto.getArea();

        if(null!=areaDTO){

            distributorAreaRelationService.deleteBatchByDistributorIds(distriburotIds);

            DistributorAreaRelationDTO areaRelationDTO = new DistributorAreaRelationDTO();

            areaRelationDTO.setDistributorId(distributorId);

            areaRelationDTO.setAreaId(areaDTO.getId());

            areaRelationDTO.setCreatedBy(createdBy);

            areaRelationDTO.setCreatedTime(createdTime);

            areaRelationDTO.setUpdatedBy(updatedBy);

            areaRelationDTO.setCreatedTime(updatedTime);

            distributorAreaRelationService.create(areaRelationDTO);
        }

        List<BankAccountDTO> bankAccountDTOS = dto.getBankAccounts();

        if(CollectionUtils.isNotEmpty(bankAccountDTOS)){

            distributorBankAccountRelationService.deleteBatchByDistributorIds(distriburotIds);

            List<DistributorBankAccountRelationDTO> barList=new ArrayList<>(bankAccountDTOS.size());

            bankAccountDTOS.forEach(accountDTO->{

                DistributorBankAccountRelationDTO barDTO=new DistributorBankAccountRelationDTO();

                barDTO.setBankAccountId(accountDTO.getId());

                barDTO.setDistributorId(distributorId);

                barDTO.setCreatedBy(createdBy);

                barDTO.setCreatedTime(createdTime);

                barDTO.setUpdatedBy(updatedBy);

                barDTO.setCreatedTime(updatedTime);

                barList.add(barDTO);
            });

            distributorBankAccountRelationService.batchCreate(barList);
        }

        distributorService.update(dto);

        return true;
    }

    @Override
    public DistributorDTO detail(Long distributorId) {

        DistributorDTO distributor = distributorService.getById(distributorId);

        List<DistributorGradeDTO> grades=getGradeInfo(distributorId);

        List<BankAccountDTO> bankAccounts=getBankAccountInfo(distributorId);

        AreaDTO area =getAreaInfo(distributorId);

        //区域信息(查到根节点)
        if(null!=area){

            distributor.setArea(area);
        }

        //等级信息-经销商:等级:体系=1:1:N
        if(CollectionUtils.isNotEmpty(grades)){

            if(1==distributor.getLimitedParent()){//如果指定上级,就只查直接上级


            }else{//如果不指定,则查所有间接上级和直接上级,但是页面不展示

            }
            distributor.setGrades(grades);

        }

        //银行账号信息
        if(CollectionUtils.isNotEmpty(bankAccounts)){

            distributor.setBankAccounts(bankAccounts);
        }

        //经销商类型中文描述
        List<Map<String, String>> list = DistributorTypeEnum.getTypeList();

        for (Map<String, String> map : list) {

            if (distributor.getDistributorType() == Integer.valueOf(map.get("code"))) {

                distributor.setDistributorTypeDesc(map.get("msg"));
            }
        }

        return distributor;
    }

    @Override
    public List<DistributorDTO> listParentDistributorsByGrade(Long gradeId) {

        DistributorGradeDTO gradeDTO=distributorGradeService.getById(gradeId);

        //根节点是最高等级,经销商不能选同级做上级
        int root=gradeDTO.getRoot();

        if(root==1){//封装枚举类
            return Collections.emptyList();
        }

        //父等级
        long parentGradeId=gradeDTO.getParentId();

        //非根节点且没有上级的,无法确定绝对路径
        if(parentGradeId==0){
            return Collections.emptyList();
        }

        //中间表选经销商ID
        List<DistributorGradeRelationDTO> dgrDTOList=
        distributorGradeRelationService.findAllByGradeId(parentGradeId);

        List<Long> distriburotIdList=new ArrayList<>(dgrDTOList.size());

        List<DistributorDTO> DistributorDTOList=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(dgrDTOList)){

            dgrDTOList.forEach(dgr->{

                Long distriburotId=dgr.getDistributorId();

                distriburotIdList.add(distriburotId);
            });

            //某个等级下的所有经销商信息
            DistributorQuery query=new DistributorQuery();

            query.setIds(distriburotIdList);

            DistributorDTOList = distributorService.findPage(query);
        }

        return DistributorDTOList;
    }

    @Override
    public AreaDTO getAreaInfo(Long distributorId){

        DistributorAreaRelationDTO bar = distributorAreaRelationService.findOneByDistributorId(distributorId);

        Long areaId=bar.getAreaId();

        if(areaId==null){
            return new AreaDTO();
        }

        return  areaService.getAreaById(areaId);
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