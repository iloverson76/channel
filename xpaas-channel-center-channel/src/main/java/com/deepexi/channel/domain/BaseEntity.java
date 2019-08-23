package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseEntity extends AbstractObject {

    @TableId
    @TableField(value = "`id`")
    private Long id;

    @TableField(value = "`tenant_id`", fill = FieldFill.INSERT)
    private String tenantId;

    @TableField(value = "`app_id`", fill = FieldFill.INSERT)
    private String appId;

    @TableLogic
    @TableField(value = "`dr`")
    private Integer dr;

    @TableField(value = "`created_time`", fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(value = "`created_by`", fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(value = "`updated_time`", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(value = "`updated_by`", fill = FieldFill.UPDATE)
    private String updatedBy;

    @TableField(value = "`remark`", fill = FieldFill.INSERT)
    private String remark;

    /**
     * 这个字段为批量插入时的扩展字段，因为用的自增主键，如果批量时需要关联id，可能导致无法关联
     * 这里可以使用这个字段在业务层面关联，批量插入后，则可以使用这个字段取得id关联一次。
     * 这个字段不入库
     */
    @TableField(exist = false)
    private Long insertSequence;
}
