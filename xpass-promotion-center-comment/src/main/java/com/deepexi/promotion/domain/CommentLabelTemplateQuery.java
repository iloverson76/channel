package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.Pageable;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
@Data
public class CommentLabelTemplateQuery extends AbstractObject implements Pageable {

    @Min(value = -1,message = "page最小为-1")
    @Builder.Default
    private Integer page = -1;

    @Min(value = 0,message = "size最小为0")
    @Builder.Default
    private Integer size = 10;

    @NotEmpty(message = "应用Id不能为空")
    private String appId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型：0系统标签  1手工标签
     */
    private Integer type;

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getSize() {
        return size;
    }
}
