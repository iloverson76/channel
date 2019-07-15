package com.deepexi.promotion.domain;

import java.util.Date;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDetailVO extends AbstractObject {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 系统业务id
     */
    private Integer appBusinessId;

    /**
     * 评价id
     */
    private Integer commentId;

    /**
     * 评价对象id
     */
    private Integer modelId;

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
    private Integer parentId;

    /**
     * 删除状态 0无效 1有效
     */
    private Boolean dr;

    /**
     * 版本号，乐观锁
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    private String remark;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 多租户标识
     */
    private String tenantId;

}

