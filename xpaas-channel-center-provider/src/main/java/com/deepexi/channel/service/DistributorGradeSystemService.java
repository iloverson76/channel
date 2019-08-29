package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcDistributorGradeSystem;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_distributor_grade_system
 */
public interface DistributorGradeSystemService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcDistributorGradeSystem> findPage(CcDistributorGradeSystem eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcDistributorGradeSystem> findAll(CcDistributorGradeSystem eo);

    /**
      获取详情
    * @return
    */
    CcDistributorGradeSystem detail(Integer pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer id, CcDistributorGradeSystem eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcDistributorGradeSystem eo);

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