package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 发布评价内容
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 10:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentInfoInputVO extends AbstractObject {

    /**
     * 当前系统业务唯一编码
     */
    @NotNull(message = "业务唯一编码不能为空")
    private String code;

    /**
     * 评价渠道 {@link com.deepexi.promotion.enums.CommentChannelEnum}
     */
    @NotNull
    private Integer channel;

    /**
     * 评价目标id  针对的对象评价，如商品id
     */
    private String targetId;

    /**
     * 多租户标识
     */
    private String tenantId;

    /**
     * 评价明细
     */
    @NotEmpty
    @Valid
    private List<CommentDetailInputVO> commentDetailList;

}
