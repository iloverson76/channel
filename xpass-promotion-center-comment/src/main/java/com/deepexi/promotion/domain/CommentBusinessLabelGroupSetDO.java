package com.deepexi.promotion.domain;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;


/**
 * 业务评价设置(标签组)
 *
 * @author admin
 */
@EqualsAndHashCode(callSuper = true)
@TableName("comment_business_label_group_set")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentBusinessLabelGroupSetDO extends SuperEntity implements Serializable {

    /**
     * 业务对象关联id
     */
    private Long  businessModelConnectId;

    /**
     * 标签组id
     */
    private Long  labelGroupId;

    /**
     * 模板设置类型 {@link com.deepexi.promotion.enums.CommentTemplateTypeEnum}
     */
    private Integer type;

}

