package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcBank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CcBankMapper extends BaseMapper<CcBank> {

    List<CcBank> findList(@Param("eo")  CcBank eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}