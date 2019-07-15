package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhoust
 * @date 2019/5/24
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateHistoryVO extends AbstractObject {
    private Long starTemplateId;
    private String starTemplateName;
    private CommentStarTemplateVO history;
    private Long appId;
    private Date updateTime;
}
