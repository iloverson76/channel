package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorGradeRelationDAO;
import com.deepexi.channel.domain.distributor.DistributorGradeRelationDO;
import com.deepexi.channel.domain.distributor.DistributorGradeRelationDTO;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import com.deepexi.channel.service.DistributorGradeRelationService;
import com.deepexi.channel.mapper.DistributorGradeRelationMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class DistributorGradeRelationServiceImpl implements DistributorGradeRelationService {

    @Autowired
    DistributorGradeRelationDAO distributorGradeRelationDAO;

    @Override
    public long create(DistributorGradeRelationDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorGradeRelationDO eo=dto.clone(DistributorGradeRelationDO.class, CloneDirection.FORWARD);

        distributorGradeRelationDAO.save(eo);

        return eo.getId();
    }

    @Override
    public boolean createBatch(List<DistributorGradeRelationDTO> dtoList) {

        if(CollectionUtils.isEmpty(dtoList)){
            return false;
        }

        return distributorGradeRelationDAO.saveBatch(ObjectCloneUtils.
                convertList(dtoList,DistributorGradeRelationDO.class,CloneDirection.FORWARD));
    }
}