package com.deepexi.channel.businness.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.deepexi.channel.businness.*;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainDetailDTO;
import com.deepexi.channel.domain.store.*;
import com.deepexi.channel.service.*;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreBusinessServiceImpl implements StoreBusinessService {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreGradeService storeGradeService;
    @Autowired
    StoreTypeService storeTypeService;
    @Autowired
    StoreGradeBusinessService storeGradeBusinessService;
    @Autowired
    StoreTypeBusinessService storeTypeBusinessService;
    @Autowired
    StoreChainBusinessService storeChainBusinessService;
    @Autowired
    StoreChainService storeChainService;
    @Autowired
    StoreAreaBusinessService storeAreaBusinessService;
    @Autowired
    StoreHistoryService storeHistoryService;
    @Autowired
    StoreDistributorBusinessService storeDistributorBusinessService;


    @Override
    @Transactional
    public Long create(StoreDetailDTO dto) {
        //新增门店基本信息
        Long id = storeService.create(dto);
        dto.setId(id);
        //新增门店类型关联
        if (dto.getStoreTypeDTO() != null) {
            Long storeTypeRelationId = storeTypeBusinessService.saveStoreTypeRelation(dto);
        }
        //新增门店等级关联
        if (dto.getStoreGradeDTO() != null) {
            Long storeGradeRelationId = storeGradeBusinessService.saveStoreGradeRelation(dto);
        }
        //新增门店连锁关联
        if (CollectionUtil.isNotEmpty(dto.getChainDTOS()) ) {
            Boolean storeChainRelation = storeChainBusinessService.saveStoreChainRelation(dto);
        }
        //新增门店区域关联
        if(CollectionUtil.isNotEmpty(dto.getAreaDTOS())){
            Boolean storeAreaRelation = storeAreaBusinessService.saveStoreAreaRelation(dto);
        }

        //新增门店经销商关联
        if(CollectionUtil.isNotEmpty(dto.getStoreDistributorDTOS())){
            Boolean storeDistributorCreate = storeDistributorBusinessService.saveStoreDistributors(dto);
        }
        return id;
    }

    @Override
    @Transactional
    public Boolean update(StoreDetailDTO dto) {
        //查询旧的数据，并保存门店历史信息
        StoreDTO storeDTO = storeService.detail(dto.getId());
        if(storeDTO == null){
            return false;
        }
        StoreHistoryDTO storeHistoryDTO = storeDTO.clone(StoreHistoryDTO.class);
        storeHistoryDTO.setStoreId(dto.getId());
        Long saveStoreHistory = storeHistoryService.create(storeHistoryDTO);
        //设置版本号
        storeHistoryDTO.setVersionNumber(this.generateHistoryVersionNumber(storeDTO));
        Long saveHistoryId = storeHistoryService.create(storeHistoryDTO);

        //修改门店基本信息
        Boolean result = storeService.update(dto);
        //修改门店等级关联
        if (dto.getStoreGradeDTO() != null) {
            Long storeGradeUpdateResult = storeGradeBusinessService.updateStoreGradeRelation(dto);
        }
        //修改门店类型关联
        if (dto.getStoreTypeDTO() != null) {
            Long storeTypeUpdateResult = storeTypeBusinessService.updateStoreTypeRelation(dto);
        }
        //修改门店连锁关联
        if (CollectionUtil.isNotEmpty(dto.getChainDTOS()) ) {
            Boolean storeChainUpdateResult = storeChainBusinessService.updateStoreChainRelation(dto);
        }
        //修改门店区域关联
        if(CollectionUtil.isNotEmpty(dto.getAreaDTOS())) {
            Boolean storeAreaUpdateResult = storeAreaBusinessService.updateStoreAreaRelation(dto);
        }
        //修改门店经销商关联
        if(CollectionUtil.isNotEmpty(dto.getStoreDistributorDTOS())) {
            Boolean storeDistributorUpdateResult = storeDistributorBusinessService.updateStoreDistributorRelation(dto);
        }
        return result;
    }

    @Override
    public StoreDetailDTO detail(Long pk) {
        StoreDTO storeDTO = storeService.detail(pk);
        if(storeDTO == null){
            return null;
        }
        StoreDetailDTO result = storeDTO.clone(StoreDetailDTO.class);
        //获取门店等级关联
        StoreGradeDTO storeGradeDTO = storeGradeBusinessService.getStroeGradeByStoreId(pk);
        result.setStoreGradeDTO(storeGradeDTO);

        //获取门店类型关联
        StoreTypeDTO storeTypeDTO = storeTypeBusinessService.getStoreTypeByStoreId(pk);
        result.setStoreTypeDTO(storeTypeDTO);

        //查询门店连锁关联
        List<ChainDetailDTO> chainDTOS = storeChainBusinessService.getStoreChainByStoreId(pk);
        result.setChainDTOS(chainDTOS);
        //查询门店区域关联
        List<AreaDTO> areaDTOS = storeAreaBusinessService.getStoreAreaByStoreId(pk);
        result.setAreaDTOS(areaDTOS);
        //查询门店经销商关联
        List<StoreDistributorDTO> storeDistributorRelationDTOS = storeDistributorBusinessService.getStoreDistributorByStoreId(pk);
        result.setStoreDistributorDTOS(storeDistributorRelationDTOS);
        //查询门店修改历史
        StoreHistoryQuery query = StoreHistoryQuery.builder().storeId(pk).build();
        query.setPage(-1);
        List<StoreHistoryDTO> storeHistoryDTOS = storeHistoryService.findPage(query);
        result.setStoreHistoryDTOS(storeHistoryDTOS);
        return result;
    }

    /**
     * @MethodName: delete
     * @Description: 批量删除门店
     * @Param: [ids]
     * @Return: java.lang.Boolean
     * @Author: mumu
     * @Date: 2019/9/11
    **/
    @Override
    public Boolean delete(List<Long> ids) {
        //批量删除门店类型关联
        Boolean storeTypeResult= storeTypeBusinessService.deleteStoreTypeRelation(ids);
        //批量删除门店等级关联
        Boolean storeGradeResult = storeGradeBusinessService.deleteStoreGradeRelation(ids);
        //批量删除门店区域关联
        Boolean storeAreaResult = storeAreaBusinessService.deleteStoreAreaRelation(ids);
        //批量删除门店连琐关联
        Boolean storeChainResult = storeChainBusinessService.deleteStoreChainRelation(ids);
        //批量删除门店经销商关联
        Boolean storeDistributor = storeDistributorBusinessService.deleteStoreDistributor(ids);
        return storeService.delete(ids);
    }

    /**
     * @MethodName: generateHistoryVersionNumber
     * @Description: 根据门店id生成历史版本号
     * @Param: [storeId]
     * @Return: java.lang.String
     * @Author: mumu
     * @Date: 2019/9/7
    **/
    private String generateHistoryVersionNumber(StoreDTO storeDTO){
        Integer count = storeHistoryService.getStoreHistoryCountByStoreId(storeDTO.getId());
        DateTime dateTime = new DateTime(storeDTO.getUpdatedTime());
        String code = dateTime.toString(DatePattern.PURE_DATE_PATTERN)+"-"+count;
        return code;
    }

}
