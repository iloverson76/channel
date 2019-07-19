package com.deepexi.promotion.domain.coupon;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * created by chenpeng on
 */
@Data
public class CouponInstanceDTO extends AbstractObject {



    @ApiModelProperty(value = "券总数")
    private int totalCount;

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
    private int couponStatus;

    @ApiModelProperty(value = "是否已删除")
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    public String createCouponCode(){
        return "YHQ-"+UUID.randomUUID().toString().toUpperCase();
    }


}
