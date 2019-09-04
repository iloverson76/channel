package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

/**
 * <p>
 * 门店等级表
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDTO extends SuperEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 门店英文名称
     */
    private String storeNameEn;

    /**
     * 客户名称
     */
    private String clientName;

    /**
     * 客户编码 
     */
    private String clientCode;

    /**
     * 门店地址
     */
    private String storeAddress;

    /**
     * 门店图片
     */
    private String storePhone;
    /**
     * 是否启用 0 禁用 1 启用
     */
    private Boolean enable;


}
