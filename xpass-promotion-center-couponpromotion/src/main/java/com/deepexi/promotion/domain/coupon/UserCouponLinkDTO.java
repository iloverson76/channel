package com.deepexi.promotion.domain.coupon;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class UserCouponLinkDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "是否已删除")
    @TableLogic
    @TableField(value = "`is_deleted`")
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_by`", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "`create_by`", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "`updated_time`", fill = FieldFill.INSERT)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "`updated_by`", fill = FieldFill.INSERT)
    private String updatedBy;


}
