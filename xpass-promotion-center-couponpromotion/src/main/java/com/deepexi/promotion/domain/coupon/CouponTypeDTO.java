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
public class CouponTypeDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠券编码")
    private String typeCode;

    @ApiModelProperty(value = "优惠券类型名称")
    private Integer typeName;

    @ApiModelProperty(value = "优惠券类型名称")
    private String desc;

    @ApiModelProperty(value = "是否已停用")
    private Integer stopped;

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
