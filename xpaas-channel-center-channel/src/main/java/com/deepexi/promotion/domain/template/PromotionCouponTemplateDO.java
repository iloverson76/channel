package com.deepexi.promotion.domain.template;

import java.util.Date;
import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券模板表
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionCouponTemplate对象", description="优惠券模板表")
public class PromotionCouponTemplateDO extends BaseEntity {



    @ApiModelProperty(value = "模板名称")
    private String templateName;

    @ApiModelProperty(value = "模板编号")
    private String templateCode;

    @ApiModelProperty(value = "优惠券类型id")
    private Long typeId;

    @ApiModelProperty(value = "面值")
    private Integer couponAmount;

    @ApiModelProperty(value = "上限")
    private Integer upperLimit;

    @ApiModelProperty(value = "下限")
    private Integer lowerLimit;

    @ApiModelProperty(value = "总数")
    private Integer totalCount;

    @ApiModelProperty(value = "有效期起始日")
    private Date effectiveStartDay;

    @ApiModelProperty(value = "有效期结束日")
    private Date effectiveEndDay;

    @ApiModelProperty(value = "是否可转让")
    private Integer isTransfered;

    @ApiModelProperty(value = "是否可退回")
    private Integer isReturned;

    @ApiModelProperty(value = "是否可叠加使用")
    private Integer isSuperimposed;

    @ApiModelProperty(value = "限额(每人最多能领取多少张张)")
    private Integer limitQuantity;

    @ApiModelProperty(value = "互斥模板")
    private String enemyTemplateId;

    @ApiModelProperty(value = "是否已发布")
    private Integer isReleased;

    @ApiModelProperty(value = "是否已停用")
    private Integer enable;


}
