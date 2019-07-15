package com.deepexi.promotion.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @ClassName CommentAppBusinessSupportSetDTO
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-05-13 17:32
 */
@Data
public class CommentAppBusinessSupportSetDTO {

    /**
     * 是否支持文本
     */
    @JSONField(name = "support_text")
    private Boolean supportText;

    /**
     * 是否支持星评
     */
    @JSONField(name = "support_star")
    private Boolean supportStar;

    /**
     * 是否支持标签
     */
    @JSONField(name = "support_label")
    private Boolean supportLabel;
}
