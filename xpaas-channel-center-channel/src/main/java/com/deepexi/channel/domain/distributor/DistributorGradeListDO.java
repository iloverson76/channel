package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="DistributorGradeList对象", description="经销商等级列表")
public class DistributorGradeListDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级分类ID
     */
    @ApiModelProperty("父级分类ID")
    private Long parentId;

    /**
     * 是否根节点 0 是 1 否
     */
    @ApiModelProperty("是否根节点,0 是 1 否")
    private Boolean root;

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
    @ApiModelProperty("经销商等级体系ID")
    private Long gradeSystemId;

    /**
     * 等级体系名称
     */
    @ApiModelProperty("经销商等级体系名称")
    private String gradeSystemName;

    /**
     * 等级体系编码
     */
    @ApiModelProperty("经销商等级体系编码")
    private String gradeSystemCode;

}
