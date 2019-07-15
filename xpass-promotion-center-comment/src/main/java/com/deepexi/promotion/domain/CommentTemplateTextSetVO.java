package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 修改文本数据VO
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentTemplateTextSetVO extends AbstractObject {
    /**
     * 业务对象关联关系主键
     */
    @NotNull(message = "业务对象关联关系主键不能为空")
    private Long businessModelConnectId;

    /**
     * 是否支持文本
     */
    @NotNull(message = "是否开启文本不能为空")
    private Boolean supportText;

    /**
     * 是否支持图片上传
     */
    @NotNull(message = "是否开启图片不能为空")
    private Boolean supportPicture;

    /**
     * 是否支持视频上传
     */
    @NotNull(message = "是否开启视频不能为空")
    private Boolean supportVideo;

//    /**
//     * 文本设置
//     */
//    @NotEmpty(message = "文本设置信息不能为空")
//    private String supportSettings;

}

