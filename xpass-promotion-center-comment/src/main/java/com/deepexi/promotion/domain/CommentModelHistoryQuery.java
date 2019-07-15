package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.util.Date;


/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentModelHistoryQuery extends AbstractObject {


    /**
     * 评价对象的id
     */
    private Long modelId;

    /**
     * 多租户标识
     */
    private String tenantId;
    /**
     * 当前页码
     */
    @Min(value = -1,message = "页码参数超范围")
    private Integer page;
    /**
     * 分页大小
     */
    @Min(value = 0,message = "分页大小超范围")
    private Integer size;

}

