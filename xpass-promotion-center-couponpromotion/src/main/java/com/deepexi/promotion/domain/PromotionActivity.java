package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "PromotionActivity对象", description = "活动表")
public class PromotionActivity extends BaseEntity {



    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动编码")
    private String activityCode;

    @ApiModelProperty(value = "描述")
    private String activityDesc;

    @ApiModelProperty(value = "是否发布")
    private Boolean isPublish;

    @ApiModelProperty(value = "是否已发放优惠券")
    private Boolean isProvideCoupon;

    @ApiModelProperty(value = "是否启用")
    private Boolean isEnable;


}
