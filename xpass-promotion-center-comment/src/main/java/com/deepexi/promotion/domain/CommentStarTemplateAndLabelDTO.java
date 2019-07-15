package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateAndLabelDTO extends AbstractObject {

    private Long id;

    private String code;

    private String name;

    private String maxValue;

    private Boolean enableGlobalInputBox;

    private Long appId;

    private List<CommentStarTemplateDetailDTO> details;




}
