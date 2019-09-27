package com.deepexi.channel.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 经销商等级表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("经销商等级")
public class DistributorGradeBusiVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 上级经销商等级编码
     */
    @ApiModelProperty("上级经销商等级编码")
    private String parentGradeCode;

    /**
     * 上级经销商等级名称
     */
    @ApiModelProperty("上级经销商等级名称")
    private String parentGradeName;

    /**
     * 所属体系
     */
    @ApiModelProperty("所属体系")
    private DistributorGradeSystemBusiVO system;

    /**
     * 直接上级
     */
    private DistributorGradeBusiVO parent;

    /**
     * 父级分类ID
     */
    @ApiModelProperty("父级分类ID")
    private Long parentId;

    /**
     * 是否根节点 0 是 1 否
     */
    @ApiModelProperty("是否根节点 0 否 1 是")
    private int root;

    /**
     * 经销商等级名称
     */
    @ApiModelProperty("经销商等级名称")
    private String distributorGradeName;

    /**
     * 经销商等级名称
     */
    @ApiModelProperty("经销商等级名称-英文")
    private String distributorGradeNameEn;

    /**
     * 经销商等级编码
     */
    @ApiModelProperty("经销商等级编码")
    private String distributorGradeCode;

    /**
     * 等级体系ID
     */
    @ApiModelProperty("等级体系ID")
    private Long gradeSystemId;

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
