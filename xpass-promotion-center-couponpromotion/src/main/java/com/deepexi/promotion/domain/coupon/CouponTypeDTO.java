package com.deepexi.promotion.domain.coupon;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "PromotionCouponType对象", description = "")
public class CouponTypeDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;


    private String typeCode;

    private Integer typeName;


    private Integer isStopped;


    private Long id;


    private String tenantId;


    private String appId;


    private Integer isDeleted;


    private LocalDateTime createdTime;


    private String createBy;

    private LocalDateTime updatedTime;


    private String updatedBy;

}
