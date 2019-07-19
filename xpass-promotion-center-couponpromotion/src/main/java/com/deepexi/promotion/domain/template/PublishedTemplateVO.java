package com.deepexi.promotion.domain.template;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * 已发布查询模板列表VO
 */
@Data
public class PublishedTemplateVO {

    private Long id;

    private String typeName;

    private String templateCode;

    private LocalDateTime createTime;

    private Integer isStopped;

    private LocalDateTime effectiveStartTime;

    private LocalDateTime effectiveEndTime;

    private Integer count;

    private Integer usedCount;

    private String templateName;

}
