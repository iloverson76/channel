package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 回复或者评论
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-15 15:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDetailReplyInputDTO extends AbstractObject {

    /**
     * 父评论id
     */
    private Long parentId;

    /**
     * 回复用户id
     */
    private String userId;

    /**
     * 评价渠道 {@link com.deepexi.promotion.enums.CommentChannelEnum}
     */
    private Integer channel;

    /**
     * 评价状态 一般 默认有两种情况
     * 1 系统设置评价不需要审核 此时这个状态为 2
     * 2 系统设置需要审核 此时这个状态为 1
     * 这是评价明细表里面的状态
     */
    private Integer checkStatus;

    /**
     * 评价资源
     */
    private List<CommentResourceInputDTO> commentResourceList;

    /**
     * 星评评价
     */
    private List<CommentStarInputDTO> commentStarList;

    /**
     * 评价标签
     */
    private List<CommentLabelInputDTO> commentLabelList;

}
