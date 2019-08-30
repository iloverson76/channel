package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreGradeDAO;
import com.deepexi.channel.domain.store.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.service.StoreGradeService;
import java.util.Arrays;import java.util.List;
import com.github.pagehelper.PageHelper;

@Service
public class StoreGradeServiceImpl implements StoreGradeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreGradeDAO storeGradeDAO;

    @Override
    public List<StoreGradeDTO> findPage(StoreGradeQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<StoreGradeDO> storeGradeDOS =  storeGradeDAO.findList(query);
        if(CollectionUtil.isEmpty(storeGradeDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(storeGradeDOS, StoreGradeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public StoreGradeDTO detail(Integer  pk) {
        StoreGradeDO storeGradeDO = storeGradeDAO.getById(pk);
        if(storeGradeDO == null ){
            return null;
        }
        return storeGradeDO.clone(StoreGradeDTO.class);
    }

    @Override
    public Boolean update(StoreGradeDTO dto) {
        if(dto == null){
            return false;
        }
        //TODO 判断编码是否重复
        StoreGradeDO storeGradeDO = dto.clone(StoreGradeDO.class);
        return storeGradeDAO.updateById(storeGradeDO);
    }
    @Override
    public Boolean create(StoreGradeDTO dto) {
        if(dto == null){
            return false;
        }
        //TODO 判断编码是否重复
        StoreGradeDO storeGradeDO = dto.clone(StoreGradeDO.class);
        return storeGradeDAO.save(storeGradeDO);
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return storeGradeDAO.removeByIds(ids);
    }

}