package com.deepexi.channel.domain.area;

import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 区域类型表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("区域类型")
public class AreaTypeVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级类型ID
     */
    @ApiModelProperty("父级类型ID")
    private Long parentId;

    /**
     * 是否限制上级 0 不限制 1 限制
     */
    @ApiModelProperty("是否限制上级 0 不限制 1 限制")
    private Boolean limitParent;

    /**
     * 区域类型名称
     */
    @ApiModelProperty("区域类型名称")
    private String areaTypeName;

    /**
     * 区域类型编码
     */
    @ApiModelProperty("区域类型编码")
    private String areaTypeCode;

    /**
     * 区域类型英文名称
     */
    @ApiModelProperty("区域类型英文名称")
    private String areaTypeNameEn;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

}
