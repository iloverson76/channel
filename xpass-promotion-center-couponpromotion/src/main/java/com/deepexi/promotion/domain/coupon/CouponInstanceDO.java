package com.deepexi.promotion.domain.coupon;

import com.deepexi.promotion.domain.BaseEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券实例表
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionCouponInstance对象", description="优惠券实例表")
public class CouponInstanceDO extends BaseEntity {

    @ApiModelProperty(value = "模板主键")
    private String templateId;

    @ApiModelProperty(value = "优惠券编码")
    private String couponCode;

    @ApiModelProperty(value = "券状态")
    private String couponStatus;

}
