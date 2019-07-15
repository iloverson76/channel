package com.deepexi.promotion.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.config.JsonDateSerializer;
import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * domain父类
 * @author yangxi
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class SuperEntity extends AbstractObject {

    /**
	 * 自增主键
	 */
	@TableId
	@TableField(value = "`id`")
	private Long id;

	/**
	 * 多租户标识
	 */
	@TableField(value = "`tenant_id`",fill = FieldFill.INSERT)
    private String tenantId;
    
    /**
	 * 备注
	 */
	@TableField(value = "`remark`")
	private String remark;

	/**
	 * 逻辑删除
	 */
	@TableLogic
	@TableField(value = "`dr`")
	private Integer dr;

	/**
	 * 创建人
	 */
	@TableField(value = "`CREATED_BY`", fill = FieldFill.INSERT)
	private String createdBy;

	/**
	 * 创建时间
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	@TableField(value = "`CREATED_TIME`", fill = FieldFill.INSERT)
	private Date createdTime;

	/**
	 * 更新人
	 */
	@TableField(value = "`UPDATED_BY`",fill = FieldFill.UPDATE)
	private String updatedBy;

	/**
	 * 更新时间
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	@TableField(value = "`UPDATED_TIME`",fill = FieldFill.UPDATE)
	private Date updatedTime;

	/**
	 * 版本锁
	 */
	@TableField(value = "`version`")
	private Integer version;
	

	/**
	 * 这个字段为批量插入时的扩展字段，因为用的自增主键，如果批量时需要关联id，可能导致无法关联
	 * 这里可以使用这个字段在业务层面关联，批量插入后，则可以使用这个字段取得id关联一次。
	 * 这个字段不入库
	 */
	@TableField(exist = false)
	private Long insertSequence;

}