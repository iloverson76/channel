package com.deepexi.channel.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("区域")
public class AreaBusiVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "是否根节点 0否 1是")
    private Integer root;

    private AreaTypeBusiVO areaType;

    /**
     * 父级分类ID
     */
    private Long parentTypeId;

    /**
     * 父节点ID
     */
    @ApiModelProperty(value = "父节点ID")
    private Long parentId;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String path;

    /**
     * 区域分类ID
     */
    @ApiModelProperty("区域分类ID")
    private Long areaTypeId;

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
    @ApiModelProperty(value = "区域英文名称")
    private String areaNameEn;

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
