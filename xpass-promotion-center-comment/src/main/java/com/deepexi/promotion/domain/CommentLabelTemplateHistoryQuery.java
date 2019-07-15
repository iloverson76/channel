package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.Pageable;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author zhoust
 * @date 2019/5/17
 **/
@Data
public class CommentLabelTemplateHistoryQuery implements Pageable {

    private Long templateId;

    @Min(value = -1L,message = "page最小为-1")
    @Builder.Default
    private Integer page = -1;

    @Min(value = 0L,message = "size最小为0")
    @Builder.Default
    private Integer size = 10;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getSize() {
        return size;
    }

}
