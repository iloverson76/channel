package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreGradeDTO;
import com.deepexi.channel.domain.store.StoreGradeQuery;

import java.util.List;

/**
 * cc_store_grade
 */
public interface StoreGradeService {

    /**
    * 分页获取列表
    * @param query
    * @return
    */
    List<StoreGradeDTO> findPage(StoreGradeQuery query);

    /**
      获取详情
    * @return
    */
    StoreGradeDTO detail(Long pk);

    /**
     更新eo
    * @param dto
    * @return
    */
    Boolean update(StoreGradeDTO dto);

    /**
    * 创建eo
    * @param dto
    * @return
    */
    Long create(StoreGradeDTO dto);

    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);

    /**
     * @MethodName: isCodeUnique
     * @Description: 判断编码是否唯一
     * @Param: [dto]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/20
    **/
    boolean isCodeUnique(StoreGradeDTO dto);

    /**
     * @MethodName: isNameUnique
     * @Description: 判断名称是否唯一
     * @Param: [storeGradeDTO]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/20
    **/
    boolean isNameUnique(StoreGradeDTO storeGradeDTO);
}