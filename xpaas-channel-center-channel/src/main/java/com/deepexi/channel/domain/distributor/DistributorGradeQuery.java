package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 等级体系表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("供应商等级查询")
public class DistributorGradeQuery extends CommQuery {

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
