package com.deepexi.promotion.domain;

import java.io.Serializable;
import java.util.Date;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentBusinessModelConnectVO extends AbstractObject implements Serializable {

    /**
     * 业务对象关联主键
     */
    private Long  id;

    /**
     * 系统业务id
     */
    @NotNull(message = "业务ID不能为空")
    private Long  businessId;

    /**
     * 评价对象ID
     */
    @NotNull(message = "对象ID不能为空")
    private Long  modelId;

    /**
     * 应用id
     */
    @NotNull(message = "应用ID不能为空")
    private Long  appId;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 是否包含回评
     */
    private Integer isReply;

}

