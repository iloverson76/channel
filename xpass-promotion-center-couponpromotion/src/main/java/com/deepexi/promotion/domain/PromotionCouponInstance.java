package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券实例表
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionCouponInstance对象", description="优惠券实例表")
public class PromotionCouponInstance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板主键")
    private Long templateId;

    @ApiModelProperty(value = "优惠券编码")
    private String couponCode;

    @ApiModelProperty(value = "券是否被活动关联(0:未被关联;1:已关联)")
    private String couponStatus;


}
