package com.deepexi.channel.domain.distributor;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@Builder
@ApiModel("经销商等级")
public class DistributorGradeVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 所属体系
     */
    @ApiModelProperty("所属体系")
    private DistributorGradeSystemVO system;

    /**
     * 路径
     */
    @ApiModelProperty("上下级路径")
    private String path;

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
