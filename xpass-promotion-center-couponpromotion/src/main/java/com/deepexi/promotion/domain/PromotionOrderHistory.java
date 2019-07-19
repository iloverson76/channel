package com.deepexi.promotion.domain;

import java.util.Date;
import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单归档表
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionOrderHistory对象", description="订单归档表")
public class PromotionOrderHistory extends BaseEntity {



    @ApiModelProperty(value = "优惠券模板标识")
    private Long templateId;

    @ApiModelProperty(value = "订单标识")
    private String orderId;

    @ApiModelProperty(value = "用户标识")
    private String userId;

    @ApiModelProperty(value = "优惠券状态")
    private Integer couponStatus;

    @ApiModelProperty(value = "优惠券标识")
    private Long couponId;

    @ApiModelProperty(value = "订单完成时间")
    private String completedTime;

    @ApiModelProperty(value = "优惠券操作类型")
    private Integer couponOperation;

    @ApiModelProperty(value = "订单归档时间")
    private Date archiveTime;


}
