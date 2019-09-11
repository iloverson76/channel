package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.config.JsonDateSerializer;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * domain父类
 *
 * @author yangxi
 */
@Setter
@Getter
public class SuperEntity extends AbstractObject implements Serializable {

    /**
     * 自增主键
     */
    @TableId
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private String tenantId;

    @TableField(fill = FieldFill.INSERT)
    private String appId;

    /**
     * 版本号，乐观锁
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer dr = 0;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;



}