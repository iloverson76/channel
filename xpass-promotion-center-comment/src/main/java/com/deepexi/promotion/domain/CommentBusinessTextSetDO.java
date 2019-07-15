package com.deepexi.promotion.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;


/**
 * @author zhangyanping
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment_business_text_set")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentBusinessTextSetDO extends SuperEntity implements Serializable {

    /**
     * 是否支持文本,图片，视频（support_text,support_picture,support_vedio）
     */
    private String supportSettings;
    /**
     * 评价次数 默认1
     */
    private Integer commentTimes;
    /**
     * 业务对象关联id
     */
    private Long  businessModelConnectId;
    /**
     * 版本号，乐观锁
     */
    private Integer  version;
    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    private String remark;

    /**
     * 模板设置类型 {@link com.deepexi.promotion.enums.CommentTemplateTypeEnum}
     */
    private Integer type;

}

