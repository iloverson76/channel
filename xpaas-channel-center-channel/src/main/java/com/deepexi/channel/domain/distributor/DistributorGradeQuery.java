package com.deepexi.channel.domain.distributor;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 等级体系表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
public class DistributorGradeQuery extends DistributorGradeSystemQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 经销商等级名称
     */
    @ApiModelProperty("经销商等级名称")
    private String distributorGradeName;

    /**
     * 经销商等级编码
     */
    @ApiModelProperty("经销商等级编码")
    private String distributorGradeCode;

}
