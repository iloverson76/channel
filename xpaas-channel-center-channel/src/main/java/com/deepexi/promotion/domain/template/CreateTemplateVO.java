package com.deepexi.promotion.domain.template;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CreateTemplateVO {

    private Long id;

    private String templateName;

    private String desc;

    private Long typeId;

    private Integer parValueType;

    private BigDecimal parValue;

    private BigDecimal upperLimit;

    private BigDecimal lowerLimit;

    private Integer totalCount;

    private Date effectiveStartTime;

    private Date effectiveEndTime;

    private Integer isTransfered;

    private Integer isReturned;


    private Integer isSuperimposed;


    private Integer isLimitQuantity;


    private Integer limitQuantityCount;


    private List<EnemyTemplateVO> enemyTemplates;

    private List<RuleVO> userRules;

    private List<RuleVO> goodRules;

    private List<RuleVO> channelRules;
}
