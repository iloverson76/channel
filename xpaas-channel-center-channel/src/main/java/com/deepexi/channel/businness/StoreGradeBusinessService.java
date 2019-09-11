package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreGradeDTO;

import java.util.List;

public interface StoreGradeBusinessService {
    Boolean deleteGradeType(List<Long> ids);

    Long saveStoreGradeRelation(StoreDetailDTO dto);

    StoreGradeDTO getStroeGradeByStoreId(Long pk);

    boolean haveStoreRelation(List<Long> ids);

    /**
     * @MethodName: updateStoreGradeRelation
     * @Description: 返回更新完的id
     * @Param: [dto]
     * @Return: java.lang.Long
     * @Author: mumu
     * @Date: 2019/9/6
    **/
    Long updateStoreGradeRelation(StoreDetailDTO dto);

    /**
     * 根据storeId批量删除门店等级关联
     * @param ids
     * @return
     */
    Boolean deleteStoreGradeRelation(List<Long> ids);
}
