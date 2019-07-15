package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;


/**
 * @author amdin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarResultVO extends AbstractObject {


    /**
     * 关联评价明细id
     */
    private Long commentDetailId;

    /**
     * 关联星评评级id
     */
    private Long starTemplateId;

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
     * 评价视频
     */
    private String commentVideo;

    /**
     * 评价时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 评价标签
     */
    private List<CommentLabelInputVO> commentLabelList;


}

