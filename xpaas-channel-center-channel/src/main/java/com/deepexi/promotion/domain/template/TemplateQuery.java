package com.deepexi.promotion.domain.template;

import lombok.Data;

import java.util.Date;

@Data
public class TemplateQuery {

    private String templateName;

    private Long typeId;

    private Date startTime;

    private Date endTime;

    /**
     * 当前页大小
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer page;
}
