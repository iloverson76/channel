package com.deepexi.promotion.domain;

import java.io.Serializable;
import java.util.Date;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentBusinessModelConnectDTO extends AbstractObject implements Serializable {

    /**
    * 业务对象关联主键
    */
    private Long id;
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
     * 是否包含回评
     */
    private Integer isReply;
}

