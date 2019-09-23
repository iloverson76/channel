package com.deepexi.channel.service.impl;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DistributorGradeSystemServiceImpl implements DistributorGradeSystemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DistributorGradeSystemDAO distributorGradeSystemDAO;

    AppRuntimeEnv appRuntimeEnv= AppRuntimeEnv.getInstance();

    @Override
    public DistributorGradeSystemDTO detail(Long  pk) {

        DistributorGradeSystemDO eo = distributorGradeSystemDAO.getById(pk);

        if(null==eo){
            return new DistributorGradeSystemDTO();
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

        long id=eo.getId();

        if (id > 0) {
            return id;
        }
        return 0L;
    }

    @Override
    public Boolean update(DistributorGradeSystemDTO dto) {

        return distributorGradeSystemDAO.updateById(dto.clone(DistributorGradeSystemDO.class,CloneDirection.FORWARD));
    }

    @Override
    public Boolean delete(List<Long> idList) {

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
    public void validateDuplicatedNameAndCode(DistributorGradeSystemDTO dto){

        DistributorGradeSystemQuery query=new DistributorGradeSystemQuery();

        List<DistributorGradeSystemDTO> pageDTO= findPage(query);

        pageDTO.forEach(page->{

            if(dto.getGradeSystemCode().equals(page.getGradeSystemCode())){

                throw new ApplicationException(ResultEnum.GRADE_SYSTEM_CODE_DUPLICATED);
            }

            if(dto.getGradeSystemName().equals(page.getGradeSystemName())){

                throw new ApplicationException(ResultEnum.GRADE_SYSTEM_NAME_DUPLICATED);
            }

        });
    }
}