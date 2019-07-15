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
public class CommentBusinessTextSetDTO extends AbstractObject implements Serializable {

    /**
    * 主键
    */
    private Long id;
    /**
    * 是否支持文本,图片，视频（support_text,support_picture,support_vedio）
    */
    private CommentSupportSettingDTO supportSettings;
    /**
    * 评价次数 默认1
    */
    private Integer commentTimes;
    /**
    * 业务对象关联id
    */
    private Long  businessModelConnectId;
    /**
    * 删除状态 0无效 1有效
    */
    private Boolean dr;
    /**
    * 版本号，乐观锁
    */
    private Integer  version;
    /**
    * 创建时间
    */
    private Date createdTime;
    /**
    * 修改时间
    */
    private Date updatedTime;
    /**
    * 备注，一些必要备注，如删除时，为什么删除
    */
    private String remark;
    /**
    * 创建人
    */
    private String createdBy;
    /**
    * 修改人
    */
    private String updatedBy;
    /**
    * 多租户标识
    */
    private String tenantId;

    /**
     * 模板设置类型 {@link com.deepexi.promotion.enums.CommentTemplateTypeEnum}
     */
    private Integer type;

}

