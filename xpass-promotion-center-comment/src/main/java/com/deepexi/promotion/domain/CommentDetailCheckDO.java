package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.*;


/**
 * 评价审核表
 *
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("comment_detail_check")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDetailCheckDO extends SuperEntity {

    /**
     * 关联评价明细id
     */
    private Long commentDetailId;

    /**
     * 审核状态（1：未审核，2：已审核，3：不通过）
     */
    private Integer checkStatus;

    /**
     * 审核用户id
     */
    private String checkUserId;

    /**
     * 审核人姓名
     */
    private String checkUserName;

    /**
     * 备注
     */
    private String comment;

}

