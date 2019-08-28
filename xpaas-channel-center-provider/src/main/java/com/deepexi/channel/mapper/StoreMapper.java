package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreMapper extends BaseMapper<CcStore> {

    List<CcStore> findList(@Param("eo")  CcStore eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}