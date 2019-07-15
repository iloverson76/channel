package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lxx
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentResourceDTO extends AbstractObject {
	
	/**
	 * 主键ID
	 */
	private Long id;

    /**
     * 关联类型（1：评价明细，2：星评评价）
     */
    private Boolean relevanceType;

    /**
     * 评价明细或星评评价表id，根据relevance_type确定
     */
    private Integer relevanceId;

    /**
     * 资源内容，图片则为图片地址，多个图片逗号分隔
     */
    private String resourceContent;

    /**
     * 资源类型（1：文本，2：图片：3：视频）
     */
    private Integer resourceType;

    /**
     * 应用id
     */
    private Long appId;
}

