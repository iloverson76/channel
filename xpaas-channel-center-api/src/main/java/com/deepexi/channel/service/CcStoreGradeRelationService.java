package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreGradeRelation;
import com.deepexi.channel.domain.dto.CcStoreGradeRelationDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_store_grade_relation
 */
public interface CcStoreGradeRelationService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcStoreGradeRelation> findPage(CcStoreGradeRelation eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcStoreGradeRelation> findAll(CcStoreGradeRelation eo);

    /**
      获取详情
    * @return
    */
    CcStoreGradeRelation detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcStoreGradeRelation eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcStoreGradeRelation eo);

    /**
     * 单个删除
    * @return
    */
    Boolean delete(Integer  pk);

    /**
     批量删除
    * @return
    */
    Boolean delete(Integer ...pk);
}