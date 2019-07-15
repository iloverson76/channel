package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评价回复/追评VO
 * @author liaoxiaoxin
 * @date 2019/5/21 16:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDetailReplyOrReviewVO extends AbstractObject {

    /**
     * 评价id
     */
    private Long id;

    /**
     * 父评价id（追评和回复有此字段）
     */
    private Long parentId;

    /**
     * 评价用户id
     */
    private String userId;

    /**
     * 评价类型（1：普通，2：追评，3：回复)
     */
    private Integer commentType;

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
     * 创建时间
     */
    private Date createdTime;
}
