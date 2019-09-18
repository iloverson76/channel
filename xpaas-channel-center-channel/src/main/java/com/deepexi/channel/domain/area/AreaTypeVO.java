package com.deepexi.channel.domain.area;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    /**
     * 链路ID
     */
    @ApiModelProperty("链路ID")
    private Long linkId;

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
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+$")
    @Size(min=1,max=16)
    private String areaTypeName;

    /**
     * 区域类型编码
     */
    @ApiModelProperty("区域类型编码")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @Size(min=1,max=16)
    private String areaTypeCode;

    /**
     * 区域类型英文名称
     */
    @ApiModelProperty("区域类型英文名称")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
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
