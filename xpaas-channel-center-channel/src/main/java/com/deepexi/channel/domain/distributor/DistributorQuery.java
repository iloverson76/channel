package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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
@ApiModel("经销商查询")
public class DistributorQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 经销商名称
     */
    @ApiModelProperty("经销商名称(中英文都可以)")
    private String distributoNname;

    /**
     * 经销商类型 1 厂商 2 经销商
     */
    @ApiModelProperty("经销商类型 1 厂商 2 经销商")
    private Integer distributorType;

    /**
     * 状态 0 禁用 1 启用
     */
    @ApiModelProperty("状态: 0 禁用 1 启用")
    private Integer enable ;

    private List<Long> ids;


}
