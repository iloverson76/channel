package com.deepexi.promotion.domain.template;

import lombok.Data;

import java.util.Date;

/**
 * 未发布查询模板列表VO
 */
@Data
public class UnpublishedTemplateVO {

    private Long id;

    private String typeName;

    private String templateCode;

    private String templateName;

    private Date saveTime;

}
