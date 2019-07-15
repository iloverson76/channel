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
public class CommentResourceVO extends AbstractObject {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 关联类型（1：评价明细，2：星评评价）
     */
    private Boolean relevanceType;

    /**
     * 评价明细或星评评价表id，根据relevance_type确定
     */
    private Integer relevanceId;

    /**
     * 资源内容，图片则为图片地址，多个图片逗号分隔
     */
    private String resourceContent;

    /**
     * 资源类型（1：文本，2：图片：3：视频）
     */
    private Integer resourceType;

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

