package com.deepexi.promotion.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateDetailSupportSettingsVO extends AbstractObject {
    /**
     * 是否支持文本
     */
    private Boolean supportText;

    /**
     * 是否支持图片上传
     */
    private Boolean supportPicture;

    /**
     * 是否支持视频上传
     */
    private Boolean supportVideo;
}
