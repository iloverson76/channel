package com.deepexi.promotion.domain;

import com.deepexi.promotion.validator.NotAllEmpty;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 回复或者评论
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-15 15:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NotAllEmpty(message = "评论内容不能为空")
public class CommentDetailReplyInputVO extends AbstractObject {

    /**
     * 父评论id
     */
    @NotNull
    private Long parentId;

    /**
     * 评价渠道 {@link com.deepexi.promotion.enums.CommentChannelEnum}
     */
    private Integer channel;

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
