package com.deepexi.channel.domain.distributor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 经销商等级表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("经销商等级")
public class DistributorGradeBusiVO extends DistributorGradeVO {

    private static final long serialVersionUID = 1L;

    /**
     * 上级经销商等级编码
     */
    @ApiModelProperty("上级经销商等级编码")
    private String parentGradeCode;

    /**
     * 上级经销商等级名称
     */
    @ApiModelProperty("上级经销商等级名称")
    private String parentGradeName;

    /**
     * 所属体系
     */
    @ApiModelProperty("所属体系")
    private DistributorGradeSystemDTO system;

}
