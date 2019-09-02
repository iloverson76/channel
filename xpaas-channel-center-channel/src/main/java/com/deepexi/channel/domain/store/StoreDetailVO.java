package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.chain.ChainVO;
import com.deepexi.channel.domain.distributor.DistributorVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class StoreDetailVO extends StoreVO {
    @ApiModelProperty(value = "门店等级")
    private StoreTypeVO storeTypeVO;

    @ApiModelProperty(value = "门店类型")
    private StoreGradeVO storeGradeVO;

    @ApiModelProperty(value = "区域信息")
    private AreaVO areaVO;

    @ApiModelProperty(value = "连锁信息")
    private ChainVO chainVO;

    @ApiModelProperty(value = "经销商信息")
    private List<DistributorVO> distributorVOS;
}
