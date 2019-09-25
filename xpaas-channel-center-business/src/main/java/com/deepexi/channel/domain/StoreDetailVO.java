package com.deepexi.channel.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
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
@ApiModel("门店详情")
public class StoreDetailVO extends StoreVO {

    /**
     * 修改历史版本号
     */
    @ApiModelProperty(value = "修改历史版本号")
    private String versionNumber;

    @ApiModelProperty(value = "门店等级", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private StoreTypeVO storeTypeVO;

    @ApiModelProperty(value = "门店类型", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private StoreGradeVO storeGradeVO;

    @ApiModelProperty(value = "区域信息", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private List<AreaBusiVO> areaVOS;

    @ApiModelProperty(value = "连锁信息", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private List<ChainDetailVO> chainVOS;

    @ApiModelProperty(value = "经销商信息")
    private List<StoreDistributorVO> storeDistributorVOS;

    @ApiModelProperty(value = "修改历史", notes = "更新、新增时不用传" )
    private List<StoreHistoryVO> storeHistoryVOS;
}