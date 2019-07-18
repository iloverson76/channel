package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动和优惠券关联表
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionCouponActivityLink对象", description="活动和优惠券关联表")
public class PromotionCouponActivityLink extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠券模板标识")
    private Long templateId;

    @ApiModelProperty(value = "优惠券(主键)")
    private Long couponId;

    @ApiModelProperty(value = "活动(主键)")
    private Long activityId;

    @ApiModelProperty(value = "是否被活动关联(0:未被活动关联;1:已被关联)")
    private Integer couponStatus;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;


}
