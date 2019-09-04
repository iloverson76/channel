package com.deepexi.channel.service;

import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.eo.CcDistributorGrade;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorGradeService {

    /**
     * 创建经销商等级
     */
    Long create(DistributorGradeDTO dto);
    /**
      获取详情
    */
    DistributorGradeDTO detail(Long pk);

    /**
     更新经销商等级
    */
    Boolean update(DistributorGradeDTO dto);

    /**
     批量删除经销商等级
    */
    Boolean delete(List<Long> ids);

    /**
     * 分页获取列表
     */
    List<DistributorGradeDTO> findPage(DistributorGradeQuery query);

    boolean validateGradeCode(String garedCode);
}