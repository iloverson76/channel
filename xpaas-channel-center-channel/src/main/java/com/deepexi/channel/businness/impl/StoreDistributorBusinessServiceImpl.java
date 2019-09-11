package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreDistributorBusinessService;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreDistributorDTO;
import com.deepexi.channel.domain.store.StoreDistributorRelationDTO;
import com.deepexi.channel.domain.store.StoreDistributorRelationQuery;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.channel.service.StoreDistributorRelationService;
import com.deepexi.util.CollectionUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        return null;
        //        List<StoreDistributorDTO> result = new LinkedList<>();
//
//        //查询所有关联的经销商id和等级id
//        StoreDistributorRelationQuery query = StoreDistributorRelationQuery.builder().storeId(storeId).build();
//        List<StoreDistributorRelationDTO>  list = storeDistributorRelationService.findList(query);
//        if(CollectionUtil.isEmpty(list)){
//            return null;
//        }
//        //查询经销商
//        List<Long> distributorIds = list.stream().map(StoreDistributorRelationDTO::getDistributorId).collect(Collectors.toList());
//        DistributorQuery distributorQuery = new DistributorQuery();
//        distributorQuery.setIds(distributorIds);
//        List<DistributorDTO> distributorDTOS = distributorService.findPage(distributorQuery);
//        Map<Long, DistributorDTO> distributorDTOMap = distributorDTOS.stream().collect(Collectors.toMap(DistributorDTO::getId,c->c));
//
//        //查询上级经销商
//        List<Long> parentDistributorIds = distributorDTOS.stream().map(DistributorDTO::getParentId).collect(Collectors.toList());
//        DistributorQuery distributorQuery2 = new DistributorQuery();
//        distributorQuery2.setIds(parentDistributorIds);
//        List<DistributorDTO> parentDistributorDTOS = distributorService.findPage(distributorQuery);
//        Map<Long, DistributorDTO> distributorParentDTOMap = distributorDTOS.stream().collect(Collectors.toMap(DistributorDTO::getId,c->c));
//
//        //查询经销商等级
//        List<Long> gradeIds = list.stream().map(StoreDistributorRelationDTO::getGradeSystemId).collect(Collectors.toList());
//        DistributorGradeSystemQuery distributorGradeSystemQuery = new DistributorGradeSystemQuery();
//        distributorGradeSystemQuery.setIds(gradeIds);
//        List<DistributorGradeSystemDTO> distributorGradeSystemDTOS =  distributorGradeSystemService.findPage(distributorGradeSystemQuery);
//        Map<Long, DistributorGradeSystemDTO> distributorGradeSystemDTOMap = distributorGradeSystemDTOS.stream().collect(Collectors.toMap(DistributorGradeSystemDTO::getId,c->c));
//
//        //拼接数据
//        list.forEach(dd->{
//            StoreDistributorDTO dto = new StoreDistributorDTO();
//            //经销商基本信息
//            DistributorDTO d = distributorDTOMap.get(dd.getDistributorId());
//           if(d != null){
//               dto.setDistributorId(d.getId());
//               dto.setDistributorCode(d.getDistributorCode());
//               dto.setDistributorName(d.getDistributorName());
//               //父级经销商信息
//               DistributorDTO parent = distributorParentDTOMap.get(d.getParentId());
//               if(parent!=null) {
//                   dto.setParentCode(parent.getDistributorCode());
//                   dto.setParentId(parent.getId());
//                   dto.setParentName(parent.getDistributorName());
//               }
//           }
//
//            //经销商等级信息
//            DistributorGradeSystemDTO distributorGradeSystemDTO = distributorGradeSystemDTOMap.get(dd.getGradeSystemId());
//            if(distributorGradeSystemDTO != null){
//                dto.setDistributorGradeSystemCode(distributorGradeSystemDTO.getGradeSystemCode());
//                dto.setDistributorGradeSystemId(distributorGradeSystemDTO.getId());
//                dto.setDistributorGradeSystemName(distributorGradeSystemDTO.getGradeSystemName());
//            }
//            result.add(dto);
//        });
//
//
//        return result;
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
        return false;
//        List<StoreDistributorDTO> storeDistributorDTOS = dto.getStoreDistributorDTOS();
//        if(CollectionUtil.isEmpty(storeDistributorDTOS)){
//            return false;
//        }
//        //新建经销商关联列表
//        List<StoreDistributorRelationDTO> relationDTOS = new LinkedList<>();
//        storeDistributorDTOS.forEach(s->{
//            StoreDistributorRelationDTO storeDistributorRelationDTO = StoreDistributorRelationDTO.builder()
//                    .storeId(dto.getId())
//                    .distributorId(s.getDistributorId())
//                    .gradeSystemId(s.getDistributorGradeSystemId()).build();
//            relationDTOS.add(storeDistributorRelationDTO);
//        });
//        //批量添加经销商列表
//        return storeDistributorRelationService.saveBatch(relationDTOS);
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