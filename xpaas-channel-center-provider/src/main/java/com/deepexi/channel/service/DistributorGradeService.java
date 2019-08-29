package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcDistributorGrade;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorGradeService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcDistributorGrade> findPage(CcDistributorGrade eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcDistributorGrade> findAll(CcDistributorGrade eo);

    /**
      获取详情
    * @return
    */
    CcDistributorGrade detail(Integer pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer id, CcDistributorGrade eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcDistributorGrade eo);

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