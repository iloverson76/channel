package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.channel.dao.StoreGradeDAO;
import com.deepexi.channel.domain.store.StoreGradeDO;
import com.deepexi.channel.domain.store.StoreGradeDTO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        //判断编码是否重复
        if(!isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        StoreGradeDO storeGradeDO = dto.clone(StoreGradeDO.class);
        return storeGradeDAO.updateById(storeGradeDO);
    }
    @Override
    public Long create(StoreGradeDTO dto) {
        if(dto == null){
            return 0L;
        }
        //判断编码是否重复
        if(!isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        StoreGradeDO storeGradeDO = dto.clone(StoreGradeDO.class);
        storeGradeDAO.save(storeGradeDO);
        return storeGradeDO.getId();
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return storeGradeDAO.removeByIds(ids);
    }


    /**
     * @MethodName: isCodeUnique
     * @Description: 判断门店等级编码是否重复
     * @Param: [code]
     * @Return: boolean 编码唯一, true 编码唯一 ， false 编码不唯一
     * @Author: mumu
     * @Date: 2019/8/30
     **/
    @Override
    public boolean isCodeUnique(StoreGradeDTO dto){
        List<StoreGradeDO> list = storeGradeDAO.list(new QueryWrapper<StoreGradeDO>().lambda()
                .eq(StoreGradeDO::getStoreGradeCode,dto.getStoreGradeCode()));
        if(CollectionUtil.isNotEmpty(list)){
            //不为空，还有可能是更新时自身的编码
            if(list.size()==1){
                StoreGradeDO StoreGradeDO = list.get(0);
                //该code是本身，不属于重复
                if(StoreGradeDO.getId().equals(dto.getId())){
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}