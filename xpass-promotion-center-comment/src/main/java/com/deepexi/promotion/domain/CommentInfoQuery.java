package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 分页查询评价列表接口
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-13 14:09
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentInfoQuery extends AbstractObject {

    /**
     * 查询的页码(-1 表示查询所有数据)
     */
    @Min(value = -1,message = "page不能小于{value}")
    private Integer page = 1;

    /**
     * 每页条数
     */
    @Min(value = 1,message = "size必须大于0")
    private Integer size = 10;

    /**
     * 系统应用id
     */
    @NotNull(message = "系统应用ID不能为空")
    private Long appId;

    /**
     * 系统应用id
     */
    private Set<Long> appIds;

    /**
     * 评价目标id 针对的对象评价，如商品id
     */
    private String targetId;

    /**
     * 评价目标id 集合 针对的对象评价，如商品id
     */
    private List<String> targetIds;

    /**
     * 评价内容
     */
    private String resourceContent;

    /**
     * 评价渠道
     */
    private Integer channel;

    /**
     * 审核状态
     */
    private Integer checkStatus;

    /**
     * 评价时间
     */
    private String commentTime;

    /**
     * 多租户标识
     */
    private String tenantId;

    /**
     * 评价对象id
     */
    private Long modelId;

    /**
     * 评价用户id
     */
    private String userId;

    /**
     * 标签id
     */
    private Long labelTemplateId;

    /**
     * 评价明细id
     */
    private Long commentDetailId;

    /**
     * 星评值查询：起始星评值
     */
    @Min(value = 1, message = "查询的星评起始值不能小于{value}")
    private Integer startStar;

    /**
     * 星评值查询：结束星评值
     */
    @Min(value = 1, message = "查询的星评结束值不能小于{value}")
    private Integer endStar;

    /**
     * 查询起始时间
     */
    private Date startTime;

    /**
     * 查询结束时间
     */
    private Date endTime;

    /**
     * 是否有回复 0 未回复 1 已回复
     */
    private Integer replyStatus;

}
