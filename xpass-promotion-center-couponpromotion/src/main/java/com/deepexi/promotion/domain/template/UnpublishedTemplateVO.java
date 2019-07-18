package com.deepexi.promotion.domain.template;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 未发布查询模板列表VO
 */
@Data
public class UnpublishedTemplateVO {

    private Long id;

    private String typeName;

    private String templateCode;

    private LocalDateTime saveTime;

}
