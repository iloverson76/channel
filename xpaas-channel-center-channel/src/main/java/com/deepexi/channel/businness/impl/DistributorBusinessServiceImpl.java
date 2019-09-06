package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorBusinessService;
import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.dao.DistributorGradeRelationDAO;
import com.deepexi.channel.domain.bank.BankAccountQuery;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.service.*;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public boolean delete(List<Long> idList) {

        //经销商信息

        //等级信息

        //经销商-等级中间表

        //等级表


        //银行信息

        //经销商-银行中间表

        //等级表



        return Boolean.TRUE;
    }

    @Override
    public List<DistributorDTO> findPage(DistributorQuery query) {

        //经销商信息
        List<DistributorDTO> butorList=distributorService.findPage(query);

        List<Long> butorIdList=new ArrayList<>();

        butorList.forEach(butor->{

            butorIdList.add(butor.getId());
        });

        //经销商-等级中间表
        List<DistributorGradeRelationDTO> dgrs=
                distributorGradeRelationService.findAllByDistributorIds(butorIdList);

        List<Long> gradeIds=new ArrayList<>();
        dgrs.forEach(dgr->{
            gradeIds.add(dgr.getDistributorGradeId());
        });

        //等级信息
        DistributorGradeQuery gradeQuery=new DistributorGradeQuery();
        gradeQuery.setIds(gradeIds);

        List<DistributorGradeDTO> grades=distributorGradeService.findPage(gradeQuery);

        //加入等级信息
        butorList.forEach(butor->{
           // butor.setGrades()
        });


        //经销商-银行中间表
        List<Long> bankAccountIds=new ArrayList<>();
        List<DistributorBankAccountRelationDTO> dars= distributorBankAccountRelationService.
                findAllByDistributorIds(butorIdList);
        dars.forEach(dar->{
            bankAccountIds.add(dar.getBankAccountId());
        });
        //银行信息
        BankAccountQuery bankAccountQuery=new BankAccountQuery();
        bankAccountQuery.setIds(bankAccountIds);
        bankAccountService.findList(bankAccountQuery);


        //加入银行信息


        return null;
    }

    @Override
    public boolean update(DistributorDTO clone) {

        return true;
    }


}