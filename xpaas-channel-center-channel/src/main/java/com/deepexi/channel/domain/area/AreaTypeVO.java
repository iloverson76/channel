package com.deepexi.channel.domain.area;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

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
public class AreaTypeVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("是否根节点")
    private Integer root;

    /**
     * 链路ID
     */
    private Long chainId;

    /**
     * 挂载的区域
     */
    @ApiModelProperty("挂载的区域")
    private List<AreaDO> areas;

    /**
     * 父级类型ID
     */
    @ApiModelProperty("父级类型ID")
    private Long parentId;

    /**
     * 上级是否限制分类 0 不限制 1 限制
     */
    @ApiModelProperty("是否限制上级 0 不限制 1 限制")
    private Integer limitParent;

    /**
     * 层级路径
     */
    @ApiModelProperty("层级路径")
    private String path;

    /**
     * 上级名称
     */
    @ApiModelProperty("上级名称-中文")
    private String parentName;

    /**
     * 上级名称
     */
    @ApiModelProperty("上级名称-英文")
    private String parentNameEn;

    /**
     * 上级编码
     */
    @ApiModelProperty("上级编码")
    private String parentCode;


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

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
