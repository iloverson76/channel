package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 星评评价 输入
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 11:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarInputDTO extends AbstractObject {

    /**
     * 星评ID
     */
    private Long id;

    /**
     * 评论明细ID
     */
    private Long commentDetailId;

    /**
     * 星评id
     */
    private Long starTemplateId;

    /**
     * 星评模板明细ID
     */
    private Long starTemplateDetailId;

    /**
     * 星评值
     */
    private Integer starValue;

    /**
     * 评价名称
     */
    private String starName;

    /**
     * 星评别名
     */
    private String starAlias;

    /**
     * 评价资源
     */
    private List<CommentResourceInputDTO> commentResourceList;

    /**
     * 评价标签
     */
    private List<CommentLabelInputDTO> commentLabelList;

}
