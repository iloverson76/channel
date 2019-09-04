package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.SuperEntity;
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
public class StoreGradeVO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 门店等级名称
     */
    @ApiModelProperty(value = "门店等级名称")
    private String storeGradeName;

    /**
     * 门店等级编码
     */
    @ApiModelProperty(value = "门店等级编码")
    private String storeGradeCode;

    /**
     * 门店等级英文名称
     */
    @ApiModelProperty(value = "门店等级英文名称")
    private String storeGradeNameEn;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}
