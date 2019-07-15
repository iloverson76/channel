package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券使用规则表
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionUsageRule对象", description="优惠券使用规则表")
public class PromotionUsageRule extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "使用规则名称")
    private String tempRuleName;

    @ApiModelProperty(value = "是否可转让")
    private Integer isTransfered;

    @ApiModelProperty(value = "是否可退回")
    private Integer isReturned;

    @ApiModelProperty(value = "是否可叠加使用")
    private Integer isSuperimposed;

    @ApiModelProperty(value = "限额(没人最多能领取N张)")
    private Integer limitQuantity;

    @ApiModelProperty(value = "互斥模板")
    private String enemyTemplateId;

    @ApiModelProperty(value = "是否已启用")
    private Integer isStopped;


}
