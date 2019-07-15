package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
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
public class CommentStarInputVO extends AbstractObject {

    /**
     * 星评id
     */
    private Long starTemplateId;

    /**
     * 星评模板明细ID
     */
    @NotNull
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
    private List<CommentResourceInputVO> commentResourceList;

    /**
     * 评价标签
     */
    private List<CommentLabelInputVO> commentLabelList;

}
