package com.deepexi.promotion.domain.template;

import lombok.Data;

import java.util.Date;


/**
 * 已发布查询模板列表VO
 */
@Data
public class PublishedTemplateVO {

    private Long id;

    private String typeName;

    private String templateCode;

    private Date createTime;

    private Integer enable;

    private Date effectiveStartTime;

    private Date effectiveEndTime;

    private Integer count;

    private Integer usedCount;

    private String templateName;

}
