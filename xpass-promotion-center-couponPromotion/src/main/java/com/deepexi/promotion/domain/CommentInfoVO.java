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
public class CommentInfoVO extends AbstractObject {

    /**
     * 主键
     */
    private Integer id;

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

