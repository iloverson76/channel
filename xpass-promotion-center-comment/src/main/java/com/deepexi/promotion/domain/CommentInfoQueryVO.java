package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-13 14:25
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CommentInfoQueryVO extends AbstractObject {

    /**
     * 评价明细id
     */
    private Long id;

    /**
     * 系统业务id
     */
    private Long appBusinessId;

    /**
     * 评价对象id
     */
    private Long modelId;

    /**
     * 评价渠道 1：H5,2：iOS，3：Android
     */
    private Integer channel;

    /**
     * 评价对象名称
     */
    private String modelName;

    /**
     * 目标id
     */
    private String targetId;

    /**
     * 目标名称
     */
    private String targetName;

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
     * 评价用户id
     */
    private String userId;

    /**
     * 审核状态（1：未审核，2：已审核，3：不通过）
     */
    private Integer checkStatus;

    /**
     * 父评价id（追评和回复有此字段）
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 星评评价详情
     */
    private List<CommentStarInputVO> commentStarList;

    /**
     * 评价标签
     */
    private List<CommentLabelInputVO> commentLabelList;

    /**
     * 是否回复 0 未回复 1 已回复
     */
    private Integer replyStatus;

    /**
     * 是否存在回复模板
     */
    private Boolean canReply;
}
