package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreDistributorBusinessService;
import com.deepexi.channel.dao.StoreDistributorRelationDAO;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.domain.store.*;
import com.deepexi.channel.service.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.netflix.discovery.converters.Auto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/7 13:59
 */
@Service
public class StoreDistributorBusinessServiceImpl implements StoreDistributorBusinessService {

    @Autowired
    StoreDistributorRelationService storeDistributorRelationService;
    @Autowired
    DistributorService distributorService;
    @Autowired
    DistributorGradeSystemService distributorGradeSystemService;
    @Autowired
    DistributorGradeRelationService distributorGradeRelationService;
    @Autowired
    StoreDistributorRelationDAO storeDistributorRelationDAO;


    /**
     * @MethodName: getStoreDistributorByStoreId
     * @Description: 根据门店id获取关联的所有经销商
     * @Param: [storeId]
     * @Return: java.util.List<com.deepexi.channel.domain.store.StoreDistributorDTO>
     * @Author: mumu
     * @Date: 2019/9/9
    **/
    @Override
    public List<StoreDistributorDTO> getStoreDistributorByStoreId(Long storeId) {
        //查询所有关联的经销商id，等级体系id，parent_id
        List<StoreDistributorDO> storeDistributorDOS = storeDistributorRelationDAO.findParentDistributorByStoreId(storeId);
        if(CollectionUtil.isEmpty(storeDistributorDOS)){
            return Collections.emptyList();
        }
        List<StoreDistributorDTO> result = ObjectCloneUtils.convertList(storeDistributorDOS, StoreDistributorDTO.class);
        Set<Long> distributorIds = new HashSet<>();
        Set<Long> gradeIds = new HashSet<>();
        //遍历得到查询的id
        for ( StoreDistributorDTO dto : result){
            distributorIds.add(dto.getDistributorId());
            distributorIds.add(dto.getParentId());
            gradeIds.add(dto.getGradeSystemId());
        }
        //查询所有经销商
        DistributorQuery distributorQuery = new DistributorQuery();
        distributorQuery.setIds(new LinkedList<>(distributorIds));
        List<DistributorDTO> distributorDTOS = distributorService.findPage(distributorQuery);
        Map<Long, DistributorDTO> distributorDTOMap = distributorDTOS.stream().collect(Collectors.toMap(DistributorDTO::getId,c->c));

        //查询经销商等级
        DistributorGradeSystemQuery distributorGradeSystemQuery = new DistributorGradeSystemQuery();
        distributorGradeSystemQuery.setIds(new LinkedList<>(gradeIds));
        List<DistributorGradeSystemDTO> distributorGradeSystemDTOS =  distributorGradeSystemService.findPage(distributorGradeSystemQuery);
        Map<Long, DistributorGradeSystemDTO> distributorGradeSystemDTOMap = distributorGradeSystemDTOS.stream().collect(Collectors.toMap(DistributorGradeSystemDTO::getId,c->c));

        //拼接数据
        result.forEach(dd->{
            //经销商基本信息
            DistributorDTO self = distributorDTOMap.get(dd.getDistributorId());
            DistributorDTO parent = distributorDTOMap.get(dd.getParentId());
            if(self != null){
               dd.setDistributorCode(self.getDistributorCode());
               dd.setDistributorName(self.getDistributorName());
               //父级经销商信息
               if(parent!=null) {
                   dd.setParentCode(parent.getDistributorCode());
                   dd.setParentName(parent.getDistributorName());
               }
           }
            //经销商等级信息
            DistributorGradeSystemDTO distributorGradeSystemDTO = distributorGradeSystemDTOMap.get(dd.getGradeSystemId());
            if(distributorGradeSystemDTO != null){
                dd.setGradeSystemCode(distributorGradeSystemDTO.getGradeSystemCode());
                dd.setGradeSystemName(distributorGradeSystemDTO.getGradeSystemName());
            }
        });
        return result;
    }

    /**
     * @MethodName: saveStoreDistributors
     * @Description: 保存门店关联多个经销商
     * @Param: [dto]
     * @Return: java.lang.Boolean
     * @Author: mumu
     * @Date: 2019/9/9
    **/
    @Override
    public Boolean saveStoreDistributors(StoreDetailDTO dto) {
        List<StoreDistributorDTO> storeDistributorDTOS = dto.getStoreDistributorDTOS();
        if(CollectionUtil.isEmpty(storeDistributorDTOS)){
            return false;
        }
        //新建经销商关联列表
        List<StoreDistributorRelationDTO> relationDTOS = new LinkedList<>();
        storeDistributorDTOS.forEach(s->{
            StoreDistributorRelationDTO storeDistributorRelationDTO = StoreDistributorRelationDTO.builder()
                    .storeId(dto.getId())
                    .distributorId(s.getDistributorId())
                    .gradeSystemId(s.getGradeSystemId()).build();
            relationDTOS.add(storeDistributorRelationDTO);
        });
        //批量添加经销商列表
        return storeDistributorRelationService.saveBatch(relationDTOS);
    }

    /**
     * @MethodName: updateStoreDistributorRelation
     * @Description: 修改门店关联多个经销商
     * @Param: [dto]
     * @Return: java.lang.Boolean
     * @Author: mumu
     * @Date: 2019/9/9
    **/
    @Override
    public Boolean updateStoreDistributorRelation(StoreDetailDTO dto) {
        //先删除旧的关联
        Boolean deleteResult = storeDistributorRelationService.deleteByStoreId(dto.getId());
        //添加新的关联
        return this.saveStoreDistributors(dto);
    }

    /**
     * @MethodName: deleteStoreDistributor
     * @Description: 根据门店id批量删除门店经销商关系
     * @Param: [ids]
     * @Return: java.lang.Boolean
     * @Author: mumu
     * @Date: 2019/9/11
    **/
    @Override
    public Boolean deleteStoreDistributor(List<Long> ids) {
        return storeDistributorRelationService.deleteByStoreIds(ids);
    }
}