package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcAreaType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CcAreaTypeMapper extends BaseMapper<CcAreaType> {

    List<CcAreaType> findList(@Param("eo")  CcAreaType eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}