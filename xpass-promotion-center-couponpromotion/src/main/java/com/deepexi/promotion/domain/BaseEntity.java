package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
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
    @TableField(value = "`is_deleted`")
    private Integer isDeleted;

    @TableField(value = "`create_by`", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(value = "`create_by`", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "`updated_time`", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(value = "`updated_by`", fill = FieldFill.UPDATE)
    private String updatedBy;
}
