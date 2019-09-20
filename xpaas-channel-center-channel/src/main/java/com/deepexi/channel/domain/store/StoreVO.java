package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class StoreVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+$",message = "名称只能为中文、数字或英文")
    @Size(min=1,max=16)
    private String storeName;

    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message="编码只能为字母和数字")
    @Size(min=1,max=16)
    private String storeCode;

    /**
     * 门店英文名称
     */
    @ApiModelProperty(value = "门店英文名称")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message="英文名称只能为字母和数字")
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
    @NonNull
    private Integer enable;


}
