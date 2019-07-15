package com.deepexi.promotion.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment_business_model_connect")
public class CommentBusinessModelConnectDO extends SuperEntity implements Serializable {

    /**
     * 系统业务id
     */
    private Long  businessId;
    /**
     * 评价对象ID
     */
    private Long  modelId;
    /**
     * 应用id
     */
    private Long  appId;
    /**
     * 版本号，乐观锁
     */
    private Integer  version;
    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    private String remark;
    /**
     * 是否包含回评
     */
    private Integer isReply;


}

