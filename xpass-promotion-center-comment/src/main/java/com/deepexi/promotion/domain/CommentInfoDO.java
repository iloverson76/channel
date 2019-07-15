package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.*;


/**
 * 评价表
 *
 * @author admin
 */

@TableName("comment_info")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentInfoDO extends SuperEntity {

    /**
     * 评价渠道 1：H5,2：iOS，3：Android
     */
    private Integer channel;

    /**
     * 评价用户id
     */
    private String userId;

    /**
     * 系统业务id
     */
    private Long appBusinessId;

    /**
     * 系统业务名称
     */
    private String appBusinessName;

    /**
     * 评价目标id，如商品id
     */
    private String targetId;

    /**
     * 应用id
     */
    private Long appId;


}

