package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

/**
 * @author liaoxiaoxin
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentStatisticDTO extends AbstractObject {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 系统业务id
     */
    private Long appBusinessId;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 关联评价对象id
     */
    private Long modelId;

    /**
     * 统计描述
     */
    private String statisticDesc;

    /**
     * 统计编码，标明某一个统计维度，这个值应该是唯一值
     */
    private String statisticCode;

    /**
     * 统计的目标id，比如统计商品，则为商品id
     */
    private String targetId;

    /**
     * 统计类型，比如 1：追评，2：回复:3：图片，4：视频，5:星评值
     */
    private Integer type;

    /**
     * 关联ID: 标签ID(labelId) 或 星评模板明细ID(starTemplateDetailId)
     */
    private Long relevanceId;

    /**
     * 关联星评id
     */
    private Long starTemplateId;

    /**
     * 统计名称(这里名称可以是：有图、视频、追加、标签名称或者星评别名)
     */
    private String name;

    /**
     * 数量
     */
    private Integer countValue;

    /**
     * 多租户标识
     */
    private String tenantId;

}

