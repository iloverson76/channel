package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity extends AbstractObject {

    @ApiModelProperty(value = "主键")
    @TableId
    @TableField(value = "`id`")
    private Long id;

    @ApiModelProperty(value = "租户")
    @TableField(value = "`tenant_id`", fill = FieldFill.INSERT)
    private String tenantId;

    @ApiModelProperty(value = "平台")
    @TableField(value = "`app_id`", fill = FieldFill.INSERT)
    private String appId;

    @ApiModelProperty(value = "是否已删除")
    @TableLogic
    @TableField(value = "`is_deleted`")
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_by`", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "`create_by`", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "`updated_time`", fill = FieldFill.INSERT)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "`updated_by`", fill = FieldFill.INSERT)
    private String updatedBy;
}
