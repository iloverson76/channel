package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;

import lombok.*;

/**
 * @author admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetailDTO extends AbstractObject {
	
	/**
	 * 主键ID
	 */
	private Long id;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 系统业务id
     */
    private Long appBusinessId;

    /**
     * 评价id
     */
    private Long commentId;

    /**
     * 评价对象id
     */
    private Long modelId;

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

}

