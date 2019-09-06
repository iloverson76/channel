package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.domain.eo.CcArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AreaMapper extends BaseMapper<AreaDO> {


    List<AreaDO> listAreaPage(AreaQuery query);
}