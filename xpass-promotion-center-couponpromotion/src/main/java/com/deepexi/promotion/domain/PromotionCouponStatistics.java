package com.deepexi.promotion.domain;

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
@ApiModel(value="PromotionCouponStatistics对象", description="优惠券统计情况")
public class PromotionCouponStatistics extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板id")
    private String templateId;

    @ApiModelProperty(value = "优惠券总数")
    private Integer totalCount;

    @ApiModelProperty(value = "活动占用总数")
    private Integer acvitityLinkCount;

    @ApiModelProperty(value = "已领用总数")
    private Integer acceptedCount;

    @ApiModelProperty(value = "已使用总数")
    private Integer usedCount;


}
