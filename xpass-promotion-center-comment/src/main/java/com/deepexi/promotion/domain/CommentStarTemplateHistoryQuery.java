package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author zhoust
 * @date 2019/5/24
 **/
@Data
public class CommentStarTemplateHistoryQuery extends AbstractObject implements Pageable {
    @Min(value = -1, message = "page最小为-1")
    @Builder.Default
    private Integer page = -1;

    @Min(value = 0, message = "size最小为0")
    @Builder.Default
    private Integer size = 10;


    private Long appId;

    private Long starTemplateId;

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getSize() {
        return size;
    }
}
