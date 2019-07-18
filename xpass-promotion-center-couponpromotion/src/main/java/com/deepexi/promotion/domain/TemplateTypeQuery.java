package com.deepexi.promotion.domain;

import lombok.Data;

@Data
public class TemplateTypeQuery {

    private String typeName;

    private String typeCode;

    private Integer size;

    private Integer page;
}
