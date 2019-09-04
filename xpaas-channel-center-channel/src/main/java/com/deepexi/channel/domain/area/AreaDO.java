package com.deepexi.channel.domain.area;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("cc_area")
@ApiModel(value="Area对象", description="区域表")
public class AreaDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "`app_id`", fill = FieldFill.INSERT)
    @ApiModelProperty("应用ID")
    private String appId;


    @TableField(value = "`tenant_Id`", fill = FieldFill.INSERT)
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 区域分类ID
     */
    private Integer areaTypeId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 区域英文名称
     */
    private String areaNameEn;

    /**
     * 描述
     */
    private String description;

}
