package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.promotion.domain.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
@TableName("comment_label_template_history")
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelTemplateHistoryDO extends SuperEntity {

    /**
     * 标签模板Id
     */
    private Long labelTemplateId;
    /**
     * 标签模板名字
     */
    private String labelTemplateName;
    /**
     * 历史记录
     */
    private String history;
    /**
     * 应用Id
     */
    private String appId;
    /**
     *更新状态，0为名字更新
     */
    private String updateType;
}
