package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseEntity extends AbstractObject {

    @TableId
    @TableField(value = "`id`")
    @ApiModelProperty(value = "主键", example = "123")
    private Long id;


    @TableField(value = "`app_id`", fill = FieldFill.INSERT)
    @ApiModelProperty("应用ID")
    private String appId;


    @TableField(value = "`tenant_Id`", fill = FieldFill.INSERT)
    @ApiModelProperty("租户ID")
    private String tenantId;

    @TableLogic
    @TableField(value = "`dr`")
    @ApiModelProperty(value = "是否已删除", example = "0")
    private Integer dr;

    @TableField(value = "`created_time`", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createdTime;

    @TableField(value = "`created_by`", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人", example = "mumu")
    private String createdBy;


    @TableField(value = "`updated_time`", fill = FieldFill.UPDATE)
    @ApiModelProperty("更新时间")
    private Date updatedTime;

    @TableField(value = "`updated_by`", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新人", example = "mumu")
    private String updatedBy;

    @TableField(value = "`remark`", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;

    @TableField(value = "`description`", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "描述", example = "描述")
    private String description;

    /**
     * 版本号，乐观锁
     */
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "版本号，乐观锁" )
    private Integer version;

    /**
     * 这个字段为批量插入时的扩展字段，因为用的自增主键，如果批量时需要关联id，可能导致无法关联
     * 这里可以使用这个字段在业务层面关联，批量插入后，则可以使用这个字段取得id关联一次。
     * 这个字段不入库
     */
    @TableField(exist = false)
    private Long insertSequence;
}
