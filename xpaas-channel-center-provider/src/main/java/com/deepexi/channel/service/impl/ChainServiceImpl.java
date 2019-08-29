package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.ChainDAO;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcChain;
import com.deepexi.channel.service.ChainService;
import com.deepexi.channel.mapper.ChainMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class ChainServiceImpl implements ChainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChainDAO chainDAO;

    @Override
    public List<ChainDTO> findPage(ChainQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<ChainDO> chainDOS =  chainDAO.findList(query);
        if(CollectionUtil.isEmpty(chainDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(chainDOS, ChainDTO.class, CloneDirection.OPPOSITE);
    }

//    @Override
//    public List<CcChain> findAll(CcChain eo) {
//        List<CcChain> list = chainMapper.findList(eo);
//        return list;
//    }
    @Override
    public ChainDTO detail(Long id) {
        ChainDO chainDO = chainDAO.getById(id);
        return chainDO.clone(ChainDTO.class,CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean update(ChainDTO dto) {
        if (dto.getId() == null || dto.getId() == 0L) {
            return false;
        }
        ChainDO chainDO = dto.clone(ChainDO.class);
        boolean result = chainDAO.updateById(chainDO);
        return result;
    }
//
//    @Override
//    public Boolean create(CcChain eo) {
//        int result = chainMapper.insert(eo);
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean delete(Integer  pk) {
//        int result = chainMapper.deleteBatchIds(Arrays.asList(pk));
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean delete(Integer ...pks) {
//        int result = chainMapper.deleteBatchIds(Arrays.asList(pks));
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }

}