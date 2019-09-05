package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.DistributorGradeDAO;
import com.deepexi.channel.domain.distributor.DistributorGradeDO;
import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributorGradeServiceImpl implements DistributorGradeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DistributorGradeDAO distributorGradeDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(DistributorGradeDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorGradeDO newNode=dto.clone(DistributorGradeDO.class, CloneDirection.FORWARD);

        distributorGradeDAO.save(newNode);

        //路径处理(id)
        long newId=newNode.getId();

        long parentId=newNode.getParentId();

        if (0==parentId) {

            newNode.setPath(String.valueOf(newId));//首次创建

        } else {

            String parent_path=distributorGradeDAO.getById(parentId).getPath()+"/"+newId;

            newNode.setPath(parent_path);
        }

        distributorGradeDAO.updateById(newNode);

        return newId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(DistributorGradeDTO dto) {

        return distributorGradeDAO.updateById(dto.clone(DistributorGradeDO.class,CloneDirection.FORWARD));
    }

    @Override
    public DistributorGradeDTO getById(Long pk) {

        if(0==pk){
            return null;
        }

        DistributorGradeDTO dto=distributorGradeDAO.getById(pk).clone(DistributorGradeDTO.class,CloneDirection.OPPOSITE);

        return dto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delete(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return false;
        }

        return distributorGradeDAO.removeByIds(ids);
    }

    @Override
    public List<DistributorGradeDTO> findPage(DistributorGradeQuery query) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<DistributorGradeDO> eoList = distributorGradeDAO.findPage(query);

        if(CollectionUtils.isEmpty(eoList)){
            new ArrayList<DistributorGradeDTO>();
        }

        return ObjectCloneUtils.convertList(eoList, DistributorGradeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public boolean validateGradeCode(String garedCode) {

        int count= distributorGradeDAO.getByCode(garedCode);
        if(count>0){
            throw new ApplicationException(ResultEnum.GRADE_NAME_DUPLICATED);

        }
        return Boolean.TRUE;
    }
}