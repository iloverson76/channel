package com.deepexi.promotion.domain.coupon;

import com.deepexi.promotion.domain.BaseEntity;
import com.deepexi.util.pojo.AbstractObject;
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
public class CouponTypeVO extends AbstractObject {

    /**
     * 当前type 的code唯一标识
     */
    private String typeCode;

    /**
     * 当前 类型名称
     */
    private String typeName;

    /**
     * id
     */
    private Long id;


}
