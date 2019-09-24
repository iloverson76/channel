package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.deepexi.channel.dao.DistributorGradeSystemDAO;
import com.deepexi.channel.domain.DistributorGradeSystemDO;
import com.deepexi.channel.domain.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DistributorGradeSystemServiceImpl implements DistributorGradeSystemService {

    @Autowired
    private DistributorGradeSystemDAO distributorGradeSystemDAO;

    @Override
    public DistributorGradeSystemDTO detail(Long  pk) {

        DistributorGradeSystemDO eo = distributorGradeSystemDAO.getById(pk);

        if(null==eo){
            return new DistributorGradeSystemDTO();
        }

        return eo.clone(DistributorGradeSystemDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public DistributorGradeSystemDTO getById(Long id) {

        if(id<=0){
            return null;
        }

        DistributorGradeSystemDO eo = distributorGradeSystemDAO.getById(id);

        if(eo==null){

            return null;
        }

        return eo.clone(DistributorGradeSystemDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public long create(DistributorGradeSystemDTO dto) {

        if(null==dto){
            return 0L;
        }

        DistributorGradeSystemDO eo=dto.clone(DistributorGradeSystemDO.class, CloneDirection.FORWARD);

        distributorGradeSystemDAO.save(eo);

        return  eo.getId();
    }

    @Override
    public Boolean update(DistributorGradeSystemDTO dto) {

        return distributorGradeSystemDAO.updateById(dto.clone(DistributorGradeSystemDO.class,CloneDirection.FORWARD));
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> idList) {

        Boolean result = distributorGradeSystemDAO.removeByIds(idList);

        return result;
    }

    @Override
    public List<DistributorGradeSystemDTO> findPage(DistributorGradeSystemQuery query ) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<DistributorGradeSystemDO> eoList = distributorGradeSystemDAO.findPage(query);

        if(CollectionUtil.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList, DistributorGradeSystemDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public List<String> listGradeSystemCode() {

        List<String> codeList=distributorGradeSystemDAO.listGradeSystemCode();

        if(CollectionUtils.isEmpty(codeList)){
            return Collections.emptyList();
        }
        return codeList;
    }

    @Override
    public List<String> listGradeSystemName() {

        List<String> nameList=distributorGradeSystemDAO.listGradeSystemName();

        if(CollectionUtils.isEmpty(nameList)){
            return Collections.emptyList();
        }
        return nameList;
    }

    @Override
    public List<String> listGradeSystemNameEn() {

        List<String> nameEnList=distributorGradeSystemDAO.listGradeSystemNameEn();

        if(CollectionUtils.isEmpty(nameEnList)){
            return Collections.emptyList();
        }
        return nameEnList;
    }
}