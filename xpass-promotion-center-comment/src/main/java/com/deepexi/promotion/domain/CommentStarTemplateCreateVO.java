package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateCreateVO extends SuperEntity {

    @NotEmpty(message = "名称不能为空")
    private String name;

    @NotNull(message = "应用Id不能为空")
    private Long appId;

}


