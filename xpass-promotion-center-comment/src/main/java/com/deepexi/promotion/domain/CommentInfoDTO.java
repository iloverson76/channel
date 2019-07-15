package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentInfoDTO extends AbstractObject {
	
	/**
	 * 主键ID
	 */
	private Long id;

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
    private Integer appBusinessId;

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
    private Integer appId;
}

