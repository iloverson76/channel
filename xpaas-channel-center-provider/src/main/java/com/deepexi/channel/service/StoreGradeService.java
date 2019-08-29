package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreGrade;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_store_grade
 */
public interface StoreGradeService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcStoreGrade> findPage(CcStoreGrade eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcStoreGrade> findAll(CcStoreGrade eo);

    /**
      获取详情
    * @return
    */
    CcStoreGrade detail(Integer pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer id, CcStoreGrade eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcStoreGrade eo);

    /**
     * 单个删除
    * @return
    */
    Boolean delete(Integer pk);

    /**
     批量删除
    * @return
    */
    Boolean delete(Integer... pk);
}