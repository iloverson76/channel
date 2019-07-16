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
public class PromotionCouponInstance extends AbstractObject {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "租户")
    private String tenantId;

    @ApiModelProperty(value = "平台")
    private String appId;

    @ApiModelProperty(value = "模板主键")
    private String templateId;

    @ApiModelProperty(value = "优惠券编码")
    private String couponCode;

    @ApiModelProperty(value = "券状态")
    private String couponStatus;

    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "更新人")
    private String updatedBy;



}
