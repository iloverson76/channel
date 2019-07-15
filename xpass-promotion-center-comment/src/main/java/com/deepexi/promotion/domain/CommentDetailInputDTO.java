package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 评价明细
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 11:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDetailInputDTO extends AbstractObject {

    /**
     * 评价的对象id
     */
    private Long modelId;

    /**
     * 评价对象名称
     */
    private String modelName;

    /**
     * 评价目标id  针对的对象评价，如商品id
     */
    private String targetId;

    /**
     * 评价资源
     */
    private List<CommentResourceInputDTO> commentResourceList;

    /**
     * 星评评价
     */
    private List<CommentStarInputDTO> commentStarList;

    /**
     * 评价标签
     */
    private List<CommentLabelInputDTO> commentLabelList;
}
