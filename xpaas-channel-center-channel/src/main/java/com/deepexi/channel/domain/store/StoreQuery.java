package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.CommQuery;
import com.deepexi.channel.domain.Pageable;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * <p>
 * 门店信息表
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("门店")
public class StoreQuery  extends CommQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称")
    private String storeName;

    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    private String storeCode;

    /**
     * 门店英文名称
     */
    @ApiModelProperty(value = "门店英文名称")
    private String storeNameEn;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String clientName;

    /**
     * 客户编码
     */
    @ApiModelProperty(value = "客户编码")
    private String clientCode;

    /**
     * 门店地址
     */
    @ApiModelProperty(value = "门店地址")
    private String storeAddress;

    /**
     * 门店图片
     */
    @ApiModelProperty(value = "门店图片")
    private String storePhone;

    /**
     * 状态 1 启用 2 禁用
     */
    @ApiModelProperty(value = "状态 1 启用 2 禁用")
    private Boolean status;
}
