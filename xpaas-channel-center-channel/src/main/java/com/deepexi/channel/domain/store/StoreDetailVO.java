package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.chain.ChainVO;
import com.deepexi.channel.domain.distributor.DistributorVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("门店详情")
public class StoreDetailVO extends StoreVO {
    @ApiModelProperty(value = "门店等级", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private StoreTypeVO storeTypeVO;

    @ApiModelProperty(value = "门店类型", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private StoreGradeVO storeGradeVO;

    @ApiModelProperty(value = "区域信息", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private AreaVO areaVO;

    @ApiModelProperty(value = "连锁信息", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private ChainVO chainVO;

    @ApiModelProperty(value = "经销商信息")
    private List<DistributorVO> distributorVOS;

    @ApiModelProperty(value = "lili")
    private List<StoreHistoryVO> storeHistoryVOS;
}