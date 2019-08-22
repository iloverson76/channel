package com.deepexi.promotion.domain;

import lombok.Data;

@Data
public class PromotionCouponTypeVO {
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
