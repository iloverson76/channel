package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import com.deepexi.channel.domain.dto.CcDistributorGradeRelationDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_distributor_grade_relation
 */
public interface CcDistributorGradeRelationService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcDistributorGradeRelation> findPage(CcDistributorGradeRelation eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcDistributorGradeRelation> findAll(CcDistributorGradeRelation eo);

    /**
      获取详情
    * @return
    */
    CcDistributorGradeRelation detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcDistributorGradeRelation eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcDistributorGradeRelation eo);

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