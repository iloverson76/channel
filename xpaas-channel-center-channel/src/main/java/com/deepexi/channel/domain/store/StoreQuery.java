package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.BaseEntity;
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
public class StoreQuery  extends AbstractObject implements Pageable{

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
    @ApiModelProperty("页码")
    @Min(value = -1,message = "page最小为-1,代表不分页")
    @Builder.Default
    private Integer page = -1;

    @ApiModelProperty("每页数量")
    @Min(value = 0,message = "size最小为0")
    @Builder.Default
    private Integer size = 10;

    /**
     * 開始時間
     */
    @ApiModelProperty("開始時間")
    private Date startTime;


    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 更新開始時間
     */
    @ApiModelProperty("更新開始時間")
    private Date updateStartTime;


    /**
     * 更新结束时间
     */
    @ApiModelProperty("更新结束时间")
    private Date updateEndTime;

}
