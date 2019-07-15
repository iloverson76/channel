package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论数量统计查询
 *
 * @author liaoxiaoxin
 * @date 2019/6/14 10:32
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CommentCountQuery extends AbstractObject implements Serializable {

    /**
     * 系统应用id
     */
    @NotNull(message = "系统应用ID不能为空")
    private Long appId;

    /**
     * 系统业务id
     */
    private Long appBusinessId;

    /**
     * 评价对象id
     */
    private Long modelId;

    /**
     * 多租户标识
     */
    private String tenantId;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 1:查询有回复记录的条数
     * 0:查询没有回复记录的条数
     * 不传，统计所有
     */
    private Integer withReply;
}
