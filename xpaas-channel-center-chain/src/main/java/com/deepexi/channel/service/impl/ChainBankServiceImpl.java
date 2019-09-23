package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.ChainBankDAO;
import com.deepexi.channel.domain.ChainBankDO;
import com.deepexi.channel.domain.ChainBankDTO;
import com.deepexi.channel.domain.ChainBankQuery;
import com.deepexi.channel.service.ChainBankService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChainBankServiceImpl implements ChainBankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public boolean delete(List<Long> ids) {
        return chainBankDAO.removeByIds(ids);
    }

    @Override
    public boolean delete(Long id) {
        return chainBankDAO.removeById(id);
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
    public boolean saveBatch(List<ChainBankDTO> chainBankDTOS) {
        List<ChainBankDO> list = ObjectCloneUtils.convertList(chainBankDTOS,ChainBankDO.class);
        return chainBankDAO.saveBatch(list);
    }

//    @Override
//    public List<ChainBankDTO> getChainBankByChainId(Long id) {
//        List<ChainBankDO> chainBankDOS = chainBankDAO.getChainBankByChainId(id);
//        if(CollectionUtil.isEmpty(chainBankDOS)){
//            return null;
//        }
//        return ObjectCloneUtils.convertList(chainBankDOS, ChainBankDTO.class, CloneDirection.OPPOSITE);
//    }

    @Override
    public boolean deleteByChainId(Long id) {
        return chainBankDAO.deleteByChainId(id);
    }

}