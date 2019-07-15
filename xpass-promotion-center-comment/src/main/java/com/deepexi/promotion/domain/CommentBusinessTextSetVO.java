package com.deepexi.promotion.domain;

import java.io.Serializable;
import java.util.Date;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author zhangyanping
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentBusinessTextSetVO extends AbstractObject implements Serializable {

    /**
     * 主键
     */
    private Long  id;

    /**
     * 是否支持文本,图片，视频（support_text,support_picture,support_vedio）
     */
    private CommentSupportSettingVO supportSettings;

    /**
     * 评价次数 默认1
     */
    private Integer commentTimes;

    /**
     * 业务对象关联id
     */
    private Long  businessModelConnectId;




}

