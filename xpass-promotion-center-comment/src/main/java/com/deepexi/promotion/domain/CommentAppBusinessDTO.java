package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;

import lombok.*;

/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentAppBusinessDTO extends AbstractObject {
	
	/**
	 * 主键ID
	 */
	private Long id;

    /**
     * 名称
     */
    private String name;
    /**
     * 应用id
     */
    private Long appId;
    /**
     * 编码
     */
    private String code;
    /**
     * 标识码
     */
    private String identificationCode;
    /**
     * 业务设置（support_text:是否支持文本，support_star:是否支持星级评价，support_label:是否支持标签。true：是 ，false:否）
     */
    private String supportSettings;
    /**
     * 版本号，乐观锁
     */
    private Integer  version;
    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    private String remark;

    /**
     * 启用/禁用
     */
    private Boolean enable;

    /**
     * 多租户标识
     */
    private String tenantId;

}

