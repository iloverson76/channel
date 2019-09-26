package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreGradeDAO;
import com.deepexi.channel.domain.StoreGradeDO;
import com.deepexi.channel.domain.StoreGradeDTO;
import com.deepexi.channel.domain.StoreGradeQuery;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
@Service
public class StoreGradeServiceImpl implements StoreGradeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreGradeDAO storeGradeDAO;

    @Override
    public List<StoreGradeDTO> findPage(StoreGradeQuery query) {
        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<StoreGradeDO> storeGradeDOS = storeGradeDAO.findList(query);
        if (CollectionUtil.isEmpty(storeGradeDOS)) {
            return null;
        }
        return ObjectCloneUtils.convertList(storeGradeDOS, StoreGradeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public StoreGradeDTO detail(Long pk) {
        StoreGradeDO storeGradeDO = storeGradeDAO.getById(pk);
        if (storeGradeDO == null) {
            return null;
        }
        return storeGradeDO.clone(StoreGradeDTO.class);
    }

    @Override
    public Boolean update(StoreGradeDTO dto) {
        if (dto == null) {
            return false;
        }
        StoreGradeDO storeGradeDO = dto.clone(StoreGradeDO.class);
        return storeGradeDAO.updateById(storeGradeDO);
    }

    @Override
    public Boolean updateBatch(List<StoreGradeDTO> dtos) {
        if (CollectionUtil.isEmpty(dtos)) {
            return false;
        }
        return storeGradeDAO.updateBatchById(ObjectCloneUtils.convertList(dtos, StoreGradeDO.class));
    }

    @Override
    public Long create(StoreGradeDTO dto) {
        StoreGradeDO storeGradeDO = dto.clone(StoreGradeDO.class);
        storeGradeDAO.save(storeGradeDO);
        return storeGradeDO.getId();
    }

    @Override
    public Boolean createBatch(List<StoreGradeDTO> dtos) {
        if (CollectionUtil.isEmpty(dtos)) {
            return false;
        }
        return storeGradeDAO.saveBatch(ObjectCloneUtils.convertList(dtos, StoreGradeDO.class));
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return false;
        }
        return storeGradeDAO.removeByIds(ids);
    }

    @Override
    public boolean isCodeUnique(StoreGradeDTO dto) {
        StoreGradeQuery query = StoreGradeQuery.builder()
                .storeGradeAccuracyCode(dto.getStoreGradeCode())
                .build();
        return this.isUnique(query, dto);
    }

    @Override
    public boolean isNameUnique(StoreGradeDTO storeGradeDTO) {
        StoreGradeQuery query = StoreGradeQuery.builder()
                .storeGradeAccuracyName(storeGradeDTO.getStoreGradeName())
                .build();
        return this.isUnique(query, storeGradeDTO);
    }

    private boolean isUnique(StoreGradeQuery query, StoreGradeDTO dto) {
        List<StoreGradeDO> list = storeGradeDAO.findList(query);
        if (CollectionUtil.isNotEmpty(list)) {
            //不为空，还有可能是更新时自身的编码
            if (list.size() == 1) {
                StoreGradeDO StoreGradeDO = list.get(0);
                //该code是本身，不属于重复
                if (StoreGradeDO.getId().equals(dto.getId())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}