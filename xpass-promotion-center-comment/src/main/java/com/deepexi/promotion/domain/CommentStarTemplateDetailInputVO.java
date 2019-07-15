package com.deepexi.promotion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zhoust
 * @date 2019/5/23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateDetailInputVO extends SuperEntity {

    @NotNull(message = "星级评价模板id不能为空")
    private Long starTemplateId;

    @NotEmpty(message = "别名不能为空")
    private String nickName;

    @NotNull(message = "分值不能为空")
    private Integer value;

    @NotNull(message = "星级评价设置不能为空")
    private CommentStarTemplateDetailSupportSettingsVO supportSettings;

//    private List<CommentLabelTemplateNameInfo> labels;
}
