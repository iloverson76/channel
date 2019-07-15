package com.deepexi.promotion.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 修改文本数据VO
 * @author zhangyanping
 * @date 2019/5/28 17:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentSupportSettingVO extends AbstractObject {
    /**
     * 是否支持文本
     */
    @JSONField(name="support_text")
    private Boolean supportText;

    /**
     * 是否支持图片上传
     */
    @JSONField(name="support_picture")
    private Boolean supportPicture;

    /**
     * 是否支持视频上传
     */
    @JSONField(name="support_video")
    private Boolean supportVideo;

}

