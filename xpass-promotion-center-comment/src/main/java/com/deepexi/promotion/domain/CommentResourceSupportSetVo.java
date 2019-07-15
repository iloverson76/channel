package com.deepexi.promotion.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName CommentResourceSupportSetVo
 * @Description
 * @Author zhoujiawen
 * @Date 2019-05-28 11:07
 */
@Data
@ApiModel("文本资源设置")
public class CommentResourceSupportSetVo {

    /**
     * 是否支持文本
     */
    @JSONField(name = "support_text")
    private Boolean supportText;

    /**
     * 是否支持图片
     */
    @JSONField(name = "support_picture")
    private Boolean supportPicture;

    /**
     * 是否支持视频
     */
    @JSONField(name = "support_video")
    private Boolean supportVideo;
}
