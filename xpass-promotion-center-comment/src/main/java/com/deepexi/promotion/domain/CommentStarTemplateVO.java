package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/24
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateVO extends AbstractObject {
    private Long id;

    private String code;

    @NotEmpty(message = "名称不能为空")
    private String name;

    private Long appId;

    private Boolean enableGlobalInputBox;

    private List<CommentStarTemplateDetailVO> details;
}
