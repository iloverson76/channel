package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.channel.dao.StoreTypeDAO;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.domain.store.StoreTypeVO;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreType;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.channel.mapper.StoreTypeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class StoreTypeServiceImpl implements StoreTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreTypeDAO storeTypeDAO;

    @Override
    public List<StoreTypeDTO> findPage(StoreTypeQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<StoreTypeDO> storeTypeDOS =  storeTypeDAO.findList(query);
        if(CollectionUtil.isEmpty(storeTypeDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(storeTypeDOS, StoreTypeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public StoreTypeDTO detail(Integer  pk) {
        StoreTypeDO storeTypeDO = storeTypeDAO.getById(pk);
        if(storeTypeDO == null){
            return null;
        }
        return storeTypeDO.clone(StoreTypeDTO.class);
    }

    @Override
    public Boolean update(StoreTypeDTO dto) {
        if(dto == null){
            return false;
        }
        //判断编码是否重复
        if(!isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        StoreTypeDO storeTypeDO = dto.clone(StoreTypeDO.class);
        return storeTypeDAO.updateById(storeTypeDO);
    }

    @Override
    public Long create(StoreTypeDTO dto) {
        if(dto == null){
            return 0L;
        }
        // 判断编码是否重复
        if(!isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        StoreTypeDO storeTypeDO = dto.clone(StoreTypeDO.class);
        storeTypeDAO.save(storeTypeDO);
        return dto.getId();
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return storeTypeDAO.removeByIds(ids);
    }

    /**
     * @MethodName: isCodeUnique
     * @Description: 判断门店类型编码是否重复
     * @Param: [code]
     * @Return: boolean 编码唯一, true 编码唯一 ， false 编码不唯一
     * @Author: mumu
     * @Date: 2019/8/30
     **/
    @Override
    public boolean isCodeUnique(StoreTypeDTO dto){
        List<StoreTypeDO> list = storeTypeDAO.list(new QueryWrapper<StoreTypeDO>().lambda()
                .eq(StoreTypeDO::getStoreTypeCode,dto.getStoreTypeCode()));
        if(CollectionUtil.isNotEmpty(list)){
            //不为空，还有可能是更新时自身的编码
            if(list.size()==1){
                StoreTypeDO StoreTypeDO = list.get(0);
                //该code是本身，不属于重复
                if(StoreTypeDO.getId().equals(dto.getId())){
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}