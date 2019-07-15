package com.deepexi.promotion.domain;


import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;


/**
* comment_business_star_set
* @author zhangyanping
*/

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentBusinessStarSetDTO extends AbstractObject implements Serializable {

    /**
     * 主键
     */
    private Long  id;
    /**
     * 评价业务主键
     */
    private Long  businessModelConnectId;

    /**
     * 模板设置类型 {@link com.deepexi.promotion.enums.CommentTemplateTypeEnum}
     */
    private Integer type;
    /**
     * 星级模板ID
     */
    private Long starTemplateId;

    private CommentStarTemplateDTO commentStarTemplateDTO;

}

