package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.AreaDO;
import com.deepexi.channel.domain.AreaQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AreaMapper extends BaseMapper<AreaDO> {


    List<AreaDO> listAreaPage(AreaQuery query);

    List<AreaDO> findTree();

    List<String> listAreaCode();

    List<String> listAreaName();

    List<String> listAreaNameEn();
}