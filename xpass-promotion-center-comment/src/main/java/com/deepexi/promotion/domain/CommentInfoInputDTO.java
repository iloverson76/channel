package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class CommentInfoInputDTO extends AbstractObject {

    /**
     * 当前系统业务ID
     */
    private Long appBusinessId;

    /**
     * 系统业务名称
     */
    private String appBusinessName;

    /**
     * 评价用户id，这里应该是路由端解析token取到
     */
    private String userId;

    /**
     * 评价渠道 {@link com.deepexi.promotion.enums.CommentChannelEnum}
     */
    private Integer channel;

    /**
     * 系统应用id
     */
    private Long appId;

    /**
     * 评价目标id  针对的对象评价，如商品id
     */
    private String targetId;

    /**
     * 评价状态 一般 默认有两种情况
     * 1 系统设置评价不需要审核 此时这个状态为 2
     * 2 系统设置需要审核 此时这个状态为 1
     * 这是评价明细表里面的状态
     */
    private Integer checkStatus;

    /**
     * 评价明细
     */
    private List<CommentDetailInputDTO> commentDetailList;

}
