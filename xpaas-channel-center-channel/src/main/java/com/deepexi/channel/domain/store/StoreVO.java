package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.chain.ChainVO;
import com.deepexi.channel.domain.distributor.DistributorVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

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
public class StoreVO extends BaseEntity {

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
     * 是否启用 0 禁用 1 启用
     */
    @ApiModelProperty(value = "是否启用 0 禁用 1 启用")
    private Boolean enable;

    @ApiModelProperty(value = "门店等级")
    private StoreTypeVO storeTypeVO;

    @ApiModelProperty(value = "门店类型")
    private StoreGradeVO storeGradeVO;

    @ApiModelProperty(value = "区域信息")
    private AreaVO areaVO;

    @ApiModelProperty(value = "连锁信息")
    private ChainVO chainVO;

    @ApiModelProperty(value = "经销商信息")
    private List<DistributorVO> distributorVOS;
}
