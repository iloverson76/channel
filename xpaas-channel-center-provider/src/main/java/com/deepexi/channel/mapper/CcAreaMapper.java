package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CcAreaMapper extends BaseMapper<CcArea> {

    List<CcArea> findList(@Param("eo")  CcArea eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}