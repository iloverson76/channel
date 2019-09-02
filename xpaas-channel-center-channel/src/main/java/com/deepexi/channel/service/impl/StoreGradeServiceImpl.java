package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public Long create(StoreGradeDTO dto) {
        if(dto == null){
            return 0L;
        }
        //TODO 判断编码是否重复
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
     * @Description: 判断门店类型编码是否重复
     * @Param: [code]
     * @Return: boolean 编码唯一, true 编码唯一 ， false 编码不唯一
     * @Author: mumu
     * @Date: 2019/8/30
     **/
//    @Override
//    public boolean isCodeUnique(StoreTypeDTO dto){
//        List<StoreTypeDO> list = storeTypeDAO.list(new QueryWrapper<StoreTypeDO>().lambda()
//                .eq(StoreTypeDO::getStoreTypeCode,dto.getStoreTypeCode())
//                .eq(StoreTypeDO::getTenantId,dto.getTenantId())
//                .eq(StoreTypeDO::getAppId,dto.getAppId()));
//        if(CollectionUtil.isNotEmpty(list)){
//            //不为空，还有可能是更新时自身的编码
//            if(list.size()==1){
//                StoreTypeDO StoreTypeDO = list.get(0);
//                //该code是本身，不属于重复
//                if(StoreTypeDO.getId().equals(dto.getId())){
//                    return true;
//                }
//            }
//            return false;
//        }
//        return true;
//    }
}