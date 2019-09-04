package com.deepexi.channel.service;

import com.deepexi.channel.domain.distributor.DistributorGradeRelationDTO;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_distributor_grade_relation
 */
public interface DistributorGradeRelationService {

    long create(DistributorGradeRelationDTO dto);

}