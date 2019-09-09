package com.deepexi.channel.domain.area;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@EqualsAndHashCode(callSuper = true)
@TableName("cc_area")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="区域树")
public class AreaTreeVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String path;

    /**
     * 叶子节点
     */
    private Set<AreaTreeVO> children;

    /**
     * 父节点ID
     */
    @ApiModelProperty("父节点ID")
    private Integer parentId;

    /**
     * 区域分类ID
     */
    @ApiModelProperty("区域分类ID")
    private Integer areaTypeId;

    /**
     * 区域分类ID
     */
    @ApiModelProperty("区域分类名称")
    private String areaTypeName;

    /**
     * 区域名称
     */
    @ApiModelProperty("区域中文名称")
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
