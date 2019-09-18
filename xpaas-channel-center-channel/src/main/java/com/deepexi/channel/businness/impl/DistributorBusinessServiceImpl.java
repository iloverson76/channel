package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.DistributorBusinessService;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountQuery;
import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.enums.DistributorTypeEnum;
import com.deepexi.channel.service.*;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;
import java.util.stream.Collectors;

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
    DistributorGradeSystemService distributorGradeSystemService;

    @Autowired
    DistributorAreaRelationService distributorAreaRelationService;

    @Autowired
    private DistributorBankAccountRelationService distributorBankAccountRelationService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    AreaService areaService;

    @Autowired
    BankService bankService;


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
        List<DistributorGradeRelationDTO> gdrList= distributor.getDistributorGradeRelation();

        if(CollectionUtils.isNotEmpty(gdrList)){

            List<DistributorGradeRelationDTO> dgrInsertList=new ArrayList<>(gdrList.size());

            gdrList.forEach(dgr->{

                DistributorGradeRelationDTO dgrDTO=new DistributorGradeRelationDTO();

                dgrDTO.setDistributorId(distId);

                dgrDTO.setGradeId(dgr.getGradeId());

                dgrDTO.setSystemId(dgr.getSystemId());

                dgrDTO.setLimitedParent(dgr.getLimitedParent());

                dgrDTO.setParentId(dgr.getParentId());

                dgrDTO.setCreatedBy(createdBy);

                dgrDTO.setCreatedTime(createdTime);

                dgrDTO.setUpdatedBy(updatedBy);

                dgrDTO.setUpdatedTime(updatedTime);

                dgrInsertList.add(dgrDTO);

            });

            distributorGradeRelationService.createBatch(dgrInsertList);
        }

        //区域信息保存
        List<DistributorAreaRelationDTO> areaList=distributor.getDistributorAreaRelation();

        if(CollectionUtils.isNotEmpty(areaList)){

            List<DistributorAreaRelationDTO> areaInsertList=new ArrayList<>();

            areaList.forEach(area->{

                DistributorAreaRelationDTO adto=new DistributorAreaRelationDTO();

                adto.setAreaId(area.getAreaId());

                adto.setDistributorId(distId);

                adto.setCreatedBy(createdBy);

                adto.setCreatedTime(createdTime);

                adto.setUpdatedBy(updatedBy);

                adto.setUpdatedTime(updatedTime);

                areaInsertList.add(adto);
            });
            distributorAreaRelationService.createBatch(areaInsertList);
        }

        //账号信息批量保存
        List<DistributorBankAccountRelationDTO> accountList=distributor.getDistributorBankAccountRelation();

        if(CollectionUtils.isNotEmpty(accountList)){

            List<DistributorBankAccountRelationDTO> barInsertList=new ArrayList<>();

            accountList.forEach(account->{
                DistributorBankAccountRelationDTO bar=new DistributorBankAccountRelationDTO();

                bar.setBankAccountId(account.getBankAccountId());

                bar.setDistributorId(distId);

                bar.setCreatedBy(createdBy);

                bar.setCreatedTime(createdTime);

                bar.setUpdatedBy(updatedBy);

                bar.setUpdatedTime(updatedTime);

                barInsertList.add(bar);

            });
            distributorBankAccountRelationService.batchCreate(barInsertList);
        }

        return distId;
    }

    @Override
    public boolean delete(List<Long> butorIdList) {

        DistributorQuery query = new DistributorQuery();

        query.setIds(butorIdList);

        List<DistributorDTO> pageList = distributorService.findPage(query);

        Map<Long, DistributorDTO> pageMap =
                pageList.stream().collect(Collectors.toMap(DistributorDTO::getId, a -> a,(k1,k2)->k1));

        //是否有下级经销商
        List<DistributorGradeRelationDTO> dgrList = distributorGradeRelationService.findAllByDistributorIds(butorIdList);

        if(CollectionUtils.isNotEmpty(dgrList)){

            dgrList.forEach(dgr->{

                butorIdList.forEach(butorId->{

                    Long parentId=dgr.getParentId();

                    if(butorId.equals(parentId)){

                        throw new ApplicationException("["+pageMap.get(parentId).getDistributorName()
                                +"]已挂载下级经销商["+pageMap.get(butorId).getDistributorName()+"],无法删除!请解除关联后再操作");
                    }
                });

            });

        }


        //删除经销商
        distributorService.deleteBatch(butorIdList);

        distributorGradeRelationService.deleteBatchByDistributorIds(butorIdList);

        distributorAreaRelationService.deleteBatchByDistributorIds(butorIdList);

        return distributorBankAccountRelationService.deleteBatchByDistributorIds(butorIdList);
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

        long distId=dto.getId();

        String createdBy=dto.getCreatedBy();
        Date createdTime=dto.getCreatedTime();
        String updatedBy=dto.getUpdatedBy();
        Date updatedTime=dto.getUpdatedTime();

        //等级信息
        List<Long> distriburotIds=new ArrayList<>(1);

        distriburotIds.add(dto.getId());

        List<DistributorGradeRelationDTO> dgrInsertList= dto.getDistributorGradeRelation();

        if(CollectionUtils.isNotEmpty(dgrInsertList)) {

            //先删除再新建
            distributorGradeRelationService.deleteByDistributorId(distId);

            dgrInsertList.forEach(dgr -> {

                dgr.setDistributorId(distId);

                dgr.setCreatedBy(createdBy);

                dgr.setCreatedTime(createdTime);

                dgr.setUpdatedBy(updatedBy);

                dgr.setUpdatedTime(updatedTime);

            });

            distributorGradeRelationService.createBatch(dgrInsertList);
        }

        //区域信息
        List<DistributorAreaRelationDTO> areaInsertList= dto.getDistributorAreaRelation();

        if(CollectionUtils.isNotEmpty(areaInsertList)) {

            //先删除再新建
            distributorAreaRelationService.deleteByDistributorId(distId);

            areaInsertList.forEach(area -> {

                area.setDistributorId(distId);

                area.setCreatedBy(createdBy);

                area.setCreatedTime(createdTime);

                area.setUpdatedBy(updatedBy);

                area.setUpdatedTime(updatedTime);
            });

            distributorAreaRelationService.createBatch(areaInsertList);
        }

        //银行账号信息
        List<DistributorBankAccountRelationDTO> bankAccountInsertList = dto.getDistributorBankAccountRelation();

        distributorBankAccountRelationService.deleteBatchByDistributorId(distId);

        if(CollectionUtils.isNotEmpty(bankAccountInsertList)){

            bankAccountInsertList.forEach(account->{

                account.setDistributorId(distId);

                account.setCreatedBy(createdBy);

                account.setCreatedTime(createdTime);

                account.setUpdatedBy(updatedBy);

                account.setCreatedTime(updatedTime);
            });
            distributorBankAccountRelationService.batchCreate(bankAccountInsertList);
        }

        distributorService.update(dto);

        return Boolean.TRUE;
    }

    @Override
    public DistributorDTO detail(Long distributorId) {

        DistributorDTO distributor = distributorService.getById(distributorId);

        List<GradeInfoDTO> grades=getGradeInfo(distributorId);

        List<BankAccountDTO> bankAccounts=getBankAccountInfo(distributorId);

       List<AreaDTO> areas =getAreaInfo(distributorId);

        //区域信息(查到根节点)
        if(CollectionUtils.isNotEmpty(areas)){

            distributor.setArea(areas);
        }

        //等级信息-经销商:等级:体系=1:1:N
        if(CollectionUtils.isNotEmpty(grades)){

            distributor.setGradeInfo(grades);
        }

        //银行账号信息
        if(CollectionUtils.isNotEmpty(bankAccounts)){

            distributor.setBankAccount(bankAccounts);
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
    public List<AreaDTO> getAreaInfo(Long distributorId){

        List<DistributorAreaRelationDTO> dars = distributorAreaRelationService.findAllByDistributorId(distributorId);

        List<Long> areaIds=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(dars)){

            dars.forEach(dar->{

                areaIds.add(dar.getAreaId());

            });
        }

        List<AreaDTO> areas = areaService.findAllByIds(areaIds);

        if(CollectionUtils.isEmpty(areas)){
            return Collections.emptyList();
        }

        return areas;
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

        List<BankAccountDTO> bankAccountDTOList= bankAccountService.findList(query);

        bankAccountDTOList.forEach(account->{

            Long bankId=account.getBankId();

            List<BankDTO> bankList=bankService.getBankByIds(Collections.singletonList(bankId));

            String bankName=bankList.get(0).getBankName();

            account.setBankName(bankName);
        });

        return bankAccountDTOList;
    }

    @Override
    public List<GradeInfoDTO> getGradeInfo(Long distributorId) {

        List<Long> butorIds=new ArrayList<>(1);

        butorIds.add(distributorId);

        List<DistributorGradeRelationDTO> relationList=
                distributorGradeRelationService.findAllByDistributorIds(butorIds);

        List<Long> gradeIdList=new ArrayList<>();

        List<Long> systemIdList=new ArrayList<>();

        List<GradeInfoDTO> gradeInfos=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(relationList)){

            //中间表数据
            gradeIdList=relationList.stream().map(DistributorGradeRelationDTO::getGradeId).
                    collect(Collectors.toList());

            systemIdList=relationList.stream().map(DistributorGradeRelationDTO::getSystemId).
                    collect(Collectors.toList());

            Map<Long, List<DistributorGradeRelationDTO>> relationSystemMap =
                    relationList.stream().collect(Collectors.groupingBy(DistributorGradeRelationDTO::getSystemId));

            DistributorGradeQuery query=new DistributorGradeQuery();

            query.setIds(gradeIdList);

            List<DistributorGradeDTO> gradeDTOS = distributorGradeService.findPage(query);

            //体系信息
            DistributorGradeSystemQuery sqry=new DistributorGradeSystemQuery();

            sqry.setIds(systemIdList);

            List<DistributorGradeSystemDTO> systemDTOS = distributorGradeSystemService.findPage(sqry);

            //组装返回页面
            for (DistributorGradeSystemDTO system:systemDTOS){

                for (DistributorGradeDTO grade: gradeDTOS){

                    Long gradeSystemId=grade.getGradeSystemId();

                    if(gradeSystemId==system.getId()){

                        GradeInfoDTO gif=new GradeInfoDTO();

                        gif.setSystemId(system.getId());

                        gif.setGradeSystemName(system.getGradeSystemName());

                        gif.setGradeSystemCode(system.getGradeSystemCode());

                        gif.setGradeId(grade.getId());

                        gif.setDistributorGradeCode(grade.getDistributorGradeCode());

                        gif.setDistributorGradeName(grade.getDistributorGradeName());

                        //中间表按体系来分组只有一条数据
                        DistributorGradeRelationDTO dgr=relationSystemMap.get(gradeSystemId).get(0);

                        Integer limitedParent=dgr.getLimitedParent();

                        if(limitedParent==1){

                            DistributorDTO dis= distributorService.getById(dgr.getParentId());

                            gif.setParentDistributorId(dis.getId());

                            gif.setParentDistributorName(dis.getDistributorName());

                            gif.setLimitedParent(limitedParent);
                        }

                        gradeInfos.add(gif);
                    }
                }
            }

        }
        return gradeInfos;
    }

}