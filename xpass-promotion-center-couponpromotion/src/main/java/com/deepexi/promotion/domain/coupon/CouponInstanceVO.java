package com.deepexi.promotion.domain.coupon;

import com.deepexi.promotion.domain.BaseEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by chenpeng on
 */
@Data
public class CouponInstanceVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "券总数")
    private int totalCount;

    @ApiModelProperty(value = "模板主键")
    private String templateId;

    @ApiModelProperty(value = "优惠券编码")
    private String couponCode;

    @ApiModelProperty(value = "券状态")
    private String couponStatus;

}
