package com.deepexi.promotion.domain.coupon;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionCouponType对象", description="")
public class CouponTypeDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠券编码")
    private String typeCode;

    @ApiModelProperty(value = "优惠券类型名称")
    private Integer typeName;

    @ApiModelProperty(value = "优惠券类型名称")
    private String desc;

    @ApiModelProperty(value = "是否已停用")
    private Integer isStopped;


}
