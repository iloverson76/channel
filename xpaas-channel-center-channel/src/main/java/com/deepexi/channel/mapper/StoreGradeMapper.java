package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStoreGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreGradeMapper extends BaseMapper<CcStoreGrade> {

    List<CcStoreGrade> findList(@Param("eo")  CcStoreGrade eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}