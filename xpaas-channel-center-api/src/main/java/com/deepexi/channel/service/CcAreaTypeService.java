package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcAreaType;
import com.deepexi.channel.domain.dto.CcAreaTypeDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_area_type
 */
public interface CcAreaTypeService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcAreaType> findPage(CcAreaType eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcAreaType> findAll(CcAreaType eo);

    /**
      获取详情
    * @return
    */
    CcAreaType detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcAreaType eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcAreaType eo);

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