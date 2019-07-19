package com.deepexi.promotion.domain.coupon;

import java.time.LocalDateTime;
import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户和优惠券关联表
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionUserCouponLink对象", description="用户和优惠券关联表")
public class UserCouponLink extends BaseEntity {



    @ApiModelProperty(value = "用户标识")
    private String userId;

    @ApiModelProperty(value = "用户名成")
    private String userName;

    @ApiModelProperty(value = "优惠券标识")
    private Long couponId;

    @ApiModelProperty(value = "模板标识")
    private Long templateId;

    @ApiModelProperty(value = "类型值名称")
    private String acceptedType;

    @ApiModelProperty(value = "是否已启用")
    private Integer couponStatus;

    @ApiModelProperty(value = "领取时间")
    private LocalDateTime acceptedTime;


}
