package com.deepexi.channel.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 门店信息表
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("门店历史")
public class StoreHistoryDetailVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    /**
     * 原门店id
     */
    @ApiModelProperty(value = "原门店id")
    private Long storeId;
    /**
     * 修改历史版本号
     */
    @ApiModelProperty(value = "修改历史版本号")
    private String versionNumber;
    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称")
    private String storeName;

    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    private String storeCode;

    /**
     * 门店英文名称
     */
    @ApiModelProperty(value = "门店英文名称")
    private String storeNameEn;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String clientName;

    /**
     * 客户编码
     */
    @ApiModelProperty(value = "客户编码")
    private String clientCode;

    /**
     * 门店地址
     */
    @ApiModelProperty(value = "门店地址")
    private String storeAddress;

    /**
     * 门店图片
     */
    @ApiModelProperty(value = "门店图片")
    private String storePhone;

    /**
     * 是否启用 0 禁用 1 启用
     */
    @ApiModelProperty(value = "是否启用 0 禁用 1 启用")
    private Integer enable;

    @ApiModelProperty(value = "门店等级", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private StoreTypeVO storeTypeVO;

    @ApiModelProperty(value = "门店类型", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private StoreGradeVO storeGradeVO;

    @ApiModelProperty(value = "区域信息", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private List<AreaBusiVO> AreaBusiVOS;

    @ApiModelProperty(value = "连锁信息", notes = "更新、新增时只需传对应id给后端，而查询时会返回完整信息")
    private List<ChainDetailVO> chainVOS;

    @ApiModelProperty(value = "经销商信息")
    private List<StoreDistributorVO> storeDistributorVOS;

    @ApiModelProperty(value = "修改历史", notes = "更新、新增时不用传" )
    private List<StoreHistoryVO> storeHistoryVOS;

}
