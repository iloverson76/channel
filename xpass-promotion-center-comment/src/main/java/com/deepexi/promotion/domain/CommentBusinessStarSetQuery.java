package com.deepexi.promotion.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;



/**
* comment_business_star_set
* @author zhangyanping
*/

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentBusinessStarSetQuery{

    /**
     * 主键
     */
    private Long id;
    /**
     * 评价业务主键
     */
    private Long  businessModelConnectId;

    /**
     * 星级模板ID
     */
    private Long  starTemplateId;
    /**
     * 评价业务主键数组
     */
    private Long[] connectIds;

}

