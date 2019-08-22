package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 促销中心字典表
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionDict对象", description="促销中心字典表")
public class PromotionDict extends BaseEntity {



    @ApiModelProperty(value = "类型编码")
    private String typeCode;

    @ApiModelProperty(value = "业务编码")
    private String bussCode;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "类型值")
    private Integer valueId;

    @ApiModelProperty(value = "类型值名称")
    private String valueName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否已启用")
    private Integer enable;


}
