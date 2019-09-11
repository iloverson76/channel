package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

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
public class StoreTypeQuery  extends CommQuery {

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

    @ApiModelProperty(value = "门店类型编码")
    private String storeTypeAccuracyCode;

    /**
     * 多个id查询
     */
    @ApiModelProperty(value = "多个id查询")
    private List<Long> ids;

    @ApiModelProperty(value = "门店类型精准名称，不会进行模糊查询")
    private String storeTypeAccuracyName;
}
