package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDetailCheckDTO extends AbstractObject {
	
	/**
	 * 主键ID
	 */
	private Long id;

    /**
     * 关联评价明细id
     */
    private Integer commentDetailId;

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

