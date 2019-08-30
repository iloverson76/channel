package com.deepexi.channel.domain.area;

import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("区域")
public class AreaVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父节点ID
     */
    @ApiModelProperty(value = "父节点ID")
    private Integer parentId;

    /**
     * 区域分类ID
     */
    @ApiModelProperty("区域分类ID")
    private Integer areaTypeId;

    /**
     * 区域名称
     */
    @ApiModelProperty("区域名称")
    private String areaName;

    /**
     * 区域编码
     */
    @ApiModelProperty("区域编码")
    private String areaCode;

    /**
     * 区域英文名称
     */
    @ApiModelProperty("区域英文名称")
    private String areaNameEn;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

}
