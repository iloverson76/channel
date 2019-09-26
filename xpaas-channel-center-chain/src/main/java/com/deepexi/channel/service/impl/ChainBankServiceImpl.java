package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.ChainBankDAO;
import com.deepexi.channel.domain.ChainBankDO;
import com.deepexi.channel.domain.ChainBankDTO;
import com.deepexi.channel.domain.ChainBankQuery;
import com.deepexi.channel.service.ChainBankService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
@Slf4j
@Service
public class ChainBankServiceImpl implements ChainBankService {

    @Autowired
    private ChainBankDAO chainBankDAO;

    @Override
    public ChainBankDTO detail(Long id) {
        ChainBankDO chainBankDO = chainBankDAO.getById(id);
        if(chainBankDO == null){
            return null;
        }
        return chainBankDO.clone(ChainBankDTO.class);
    }

    @Override
    public boolean update(ChainBankDTO dto) {
        if(dto == null){
            return false;
        }
        ChainBankDO chainBankDO = dto.clone(ChainBankDO.class);
        return chainBankDAO.updateById(chainBankDO);
    }

    @Override
    public boolean updateBatch(List<ChainBankDTO> dtos) {
        if(CollectionUtil.isEmpty(dtos)){
            return false;
        }
        List<ChainBankDO> list = ObjectCloneUtils.convertList(dtos, ChainBankDO.class);
        return chainBankDAO.updateBatchById(list);
    }

    @Override
    public boolean delete(List<Long> ids) {
        return chainBankDAO.removeByIds(ids);
    }

    @Override
    public List<ChainBankDTO> findList(ChainBankQuery query) {
        if (query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<ChainBankDO> list = chainBankDAO.findList(query);
        if(CollectionUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return ObjectCloneUtils.convertList(list, ChainBankDTO.class);
    }

    @Override
    public boolean save(ChainBankDTO dto) {
        if(null == dto){
            return false;
        }
        return chainBankDAO.save(dto.clone(ChainBankDO.class));
    }

    @Override
    public boolean saveBatch(List<ChainBankDTO> chainBankDTOS) {
        List<ChainBankDO> list = ObjectCloneUtils.convertList(chainBankDTOS,ChainBankDO.class);
        return chainBankDAO.saveBatch(list);
    }

    @Override
    public boolean deleteByChainId(Long id) {
        return chainBankDAO.deleteByChainId(id);
    }

}