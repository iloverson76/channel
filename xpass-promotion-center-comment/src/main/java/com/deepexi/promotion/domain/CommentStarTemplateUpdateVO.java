package com.deepexi.promotion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author zhoust
 * @date 2019/5/23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateUpdateVO extends SuperEntity {


    private Boolean enableGlobalInputBox;

    private String name;

    private Long appId;
}
