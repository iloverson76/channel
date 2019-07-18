package com.deepexi.promotion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateTypeUpdateVO extends TemplateTypeAddVO {

    private Long id;
}
