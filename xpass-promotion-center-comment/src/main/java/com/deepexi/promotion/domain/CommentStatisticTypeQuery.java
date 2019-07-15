package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 根据评论统计分类查询评价内容
 *
 * @author liaoxiaoxin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStatisticTypeQuery extends AbstractObject implements Serializable {

    /**
     * 查询的页码(-1 表示查询所有数据)
     */
    @Min(value = -1, message = "page不能小于{value}")
    private Integer page = 1;

    /**
     * 每页条数
     */
    @Min(value = 0, message = "size不能小于{value}")
    private Integer size = 10;

    /**
     * 系统业务id
     */
    @NotNull(message = "业务ID不能为空")
    private Long appBusinessId;

    /**
     * 应用id
     */
    @NotNull(message = "应用ID不能为空")
    private Long appId;

    /**
     * 关联评价对象id
     */
    @NotNull(message = "评价对象ID不能为空")
    private Long modelId;

    /**
     * 统计的目标id，比如统计商品，则为商品id
     */
    @NotEmpty(message = "评价目标ID不能为空")
    private String targetId;

    /**
     * 统计类型 {@link com.deepexi.promotion.enums.CommentStatisticTypeEnum}
     */
    @NotNull(message = "统计类型不能为空")
    private Integer type;

    /**
     * 关联ID: 标签ID(labelId) 或 星评模板明细ID(starTemplateDetailId)
     */
    private Long relevanceId;

    /**
     * 星评模板明细ID
     */
    private Long starTemplateDetailId;

    /**
     * 审核状态
     */
    private Integer checkStatus;

    /**
     * 资源类型
     */
    private Integer resourceType;

    /**
     * 评论类型
     */
    private Integer commentType;

    /**
     * 租户ID
     */
    private String tenantId;

}

