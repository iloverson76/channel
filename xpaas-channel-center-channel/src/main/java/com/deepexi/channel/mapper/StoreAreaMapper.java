package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStoreArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreAreaMapper extends BaseMapper<CcStoreArea> {

    List<CcStoreArea> findList(@Param("eo")  CcStoreArea eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}