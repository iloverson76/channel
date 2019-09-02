package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreGrade;
import com.deepexi.channel.domain.store.StoreGradeDTO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.util.pageHelper.PageBean;

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
//    /**
//    * 获取列表
//    * @return
//    */
//    List<CcStoreGrade> findAll(CcStoreGrade eo);

    /**
      获取详情
    * @return
    */
    StoreGradeDTO detail(Integer pk);

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

//    /**
//     * 单个删除
//    * @return
//    */
//    Boolean delete(Integer pk);

    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);
}