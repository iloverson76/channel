package com.deepexi.promotion.domain;


import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


/**
* comment_business_star_set
* @author zhangyanping
*/

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentBusinessStarSetVO extends AbstractObject implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 评价业务主键
     */
    @NotNull(message = "评价对象关联主键不能为空")
    private Long  businessModelConnectId;

    /**
     * 星级模板ID
     */
    @NotNull(message = "星级模板主键不能为空")
    private Long starTemplateId;

}

