package com.deepexi.promotion.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateDetailSupportSettingsDTO extends SuperEntity {

    /**
     * 是否支持文本
     */
    @JSONField(name = "support_text")
    private Boolean supportText;

    /**
     * 是否支持
     */
    @JSONField(name = "support_picture")
    private Boolean supportPicture;

    /**
     * 是否支持标签
     */
    @JSONField(name = "support_video")
    private Boolean supportVideo;
}
