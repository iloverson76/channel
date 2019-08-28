package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStoreType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreTypeMapper extends BaseMapper<CcStoreType> {

    List<CcStoreType> findList(@Param("eo")  CcStoreType eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}