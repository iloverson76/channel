package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 评价明细，前端输入
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 11:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDetailInputVO extends AbstractObject {

    /**
     * 评价的对象id
     */
    @NotNull
    private Long modelId;

    /**
     * 评价对象名称
     */
    @NotBlank
    private String modelName;

    /**
     * 评价目标id  针对的对象评价，如商品id
     */
    @NotBlank
    private String targetId;

    /**
     * 评价资源
     */
    @Valid
    private List<CommentResourceInputVO> commentResourceList;

    /**
     * 星评评价
     */
    @Valid
    private List<CommentStarInputVO> commentStarList;

    /**
     * 评价标签
     */
    @Valid
    private List<CommentLabelInputVO> commentLabelList;
}
