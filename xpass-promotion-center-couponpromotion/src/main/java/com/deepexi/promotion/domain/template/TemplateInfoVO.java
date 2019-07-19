package com.deepexi.promotion.domain.template;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板详情
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateInfoVO extends CreateTemplateVO {

    private Integer isReleased;


    private Integer enable;


    private String typeName;
}
