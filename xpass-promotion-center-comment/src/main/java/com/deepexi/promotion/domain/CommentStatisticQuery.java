package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 评论统计查询
 *
 * @author liaoxiaoxin
 * @date 2019/6/14 10:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStatisticQuery extends AbstractObject implements Serializable {

    /**
     * 系统业务id
     */
    @NotNull(message = "系统业务ID不能为空")
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
     * 多租户标识
     */
    private String tenantId;
}
