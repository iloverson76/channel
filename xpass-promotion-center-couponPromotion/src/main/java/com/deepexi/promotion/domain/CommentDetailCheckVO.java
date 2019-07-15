package com.deepexi.promotion.domain;

import java.util.Date;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDetailCheckVO extends AbstractObject {

    /**
     * 主键
     */
    private Integer id;

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

