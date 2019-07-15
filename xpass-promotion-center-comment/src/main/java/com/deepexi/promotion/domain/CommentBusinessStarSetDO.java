package com.deepexi.promotion.domain;


import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


/**
* comment_business_star_set
* @author zhangyanping
*/

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment_business_star_set")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentBusinessStarSetDO extends SuperEntity implements Serializable {

    /**
     * 评价业务主键
     */
    @TableField(value = "`business_model_connect_id`")
    private Long  businessModelConnectId;

    /**
     * 星级模板ID
     */
    @TableField(value = "`star_template_id`")
    private Long  starTemplateId;

    /**
     * 模板设置类型 {@link com.deepexi.promotion.enums.CommentTemplateTypeEnum}
     */
    private Integer type;
}

