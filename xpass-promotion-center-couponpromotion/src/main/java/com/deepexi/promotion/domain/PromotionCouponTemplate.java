package com.deepexi.promotion.domain;

import java.time.LocalDateTime;
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
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionCouponTemplate对象", description="优惠券模板表")
public class PromotionCouponTemplate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板名称")
    private String templateName;

    @ApiModelProperty(value = "优惠券类型")
    private String couponType;

    @ApiModelProperty(value = "面值")
    private Integer couponAmount;

    @ApiModelProperty(value = "上限")
    private Integer upperLimit;

    @ApiModelProperty(value = "下限")
    private Integer lowerLimit;

    @ApiModelProperty(value = "总数")
    private Integer totalCount;

    @ApiModelProperty(value = "有效期起始日")
    private LocalDateTime effectiveStartDay;

    @ApiModelProperty(value = "有效期结束日")
    private LocalDateTime effectiveEndDay;

    @ApiModelProperty(value = "模板使用规则")
    private String usageRuleId;

    @ApiModelProperty(value = "是否已发布")
    private Integer isReleased;

    @ApiModelProperty(value = "是否已停用")
    private Integer isStopped;


}
