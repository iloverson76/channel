package com.deepexi.channel.domain;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 门店等级表
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
@ApiModel("门店等级")
public class StoreGradeQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "123")
    private Long id;

    /**
     * 门店等级名称
     */
    @ApiModelProperty(value = "门店等级名称模糊查询")
    private String storeGradeName;
    /**
     * 门店等级名称精准查询
     */
    @ApiModelProperty(value = "门店等级名称精准查询")
    private String storeGradeAccuracyName;
    /**
     * 门店等级编码
     */
    @ApiModelProperty(value = "门店等级编码，模糊查询")
    private String storeGradeCode;

    /**
     * 门店等级编码
     */
    @ApiModelProperty(value = "门店等级编码，准确查询")
    private String storeGradeAccuracyCode;
}
