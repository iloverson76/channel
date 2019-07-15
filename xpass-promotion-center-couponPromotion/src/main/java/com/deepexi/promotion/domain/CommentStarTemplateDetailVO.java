package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateDetailVO extends AbstractObject {

    private Long id;

    private String starTemplateId;


    @NotEmpty(message = "等级别名不能为空")
    private String nickName;

    @NotNull(message = "等级分值不能为空")
    private Integer value;

    private CommentStarTemplateDetailSupportSettingsVO supportSettings;

    private List<CommentLabelTemplateVO> labels;

    /**
     * 标签列表
     */
    private List<CommentLabelDetailVO> labelList;
}
