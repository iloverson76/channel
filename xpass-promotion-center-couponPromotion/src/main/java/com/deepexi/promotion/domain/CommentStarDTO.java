package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarDTO extends AbstractObject implements CommentResources {
	
	/**
	 * 主键ID
	 */
	private Long id;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 关联评价明细id
     */
    private Long commentDetailId;

    /**
     * 关联星评评级id
     */
    private Long starTemplateId;

    /**
     * 关联星评模板明细ID
     */
    private Long starTemplateDetailId;

    /**
     * 星评别名
     */
    private String starAlias;

    /**
     * 星评值
     */
    private Integer starValue;

    /**
     * 星评名称
     */
    private String starName;

    /**
     * 评价文本
     */
    private String commentText;

    /**
     * 评价图片
     */
    private String commentImage;

    /**
     * 评价时间
     */
    private Date createdTime;

    /**
     * 评价图片
     */
    private String commentVideo;

    /**
     * 评价标签
     */
    private List<CommentLabelDTO> commentLabelList;
}

