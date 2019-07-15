package com.deepexi.promotion.domain;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentModelDTO extends AbstractObject {
	
	/**
	 * 主键ID
	 */
	private Long id;

    /**
     * 应用id
     */
    private Long  appId;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 编码
     */
    private String  code;
    /**
     * 标识码
     */
    @NotBlank(message = "标识码不能为空")
    private String identificationCode;
    /**
     * 版本号，乐观锁
     */
    private Integer  version;
    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    private String remark;
    /**
     * 启用状态 0 禁用 1启用
     */
    @NotNull(message = "是否开启状态不能为空")
    private Integer isEnable;
    /**
     * 删除状态 0无效 1有效
     */
    private Boolean dr;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 多租户标识
     */
    private String tenantId;

    /**
     * 是否创建
     */
    private Boolean isCreate;
}

