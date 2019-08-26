package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 门店类型表
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
@ApiModel("门店类型")
public class StoreTypeVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 门店类型名称
     */
    @ApiModelProperty(value = "门店类型名称")
    private String storeTypeName;

    /**
     * 门店类型编码
     */
    @ApiModelProperty(value = "门店类型编码")
    private String storeTypeCode;

    /**
     * 门店类型英文名称
     */
    @ApiModelProperty(value = "门店类型英文名称")
    private String storeTypeNameEn;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}
