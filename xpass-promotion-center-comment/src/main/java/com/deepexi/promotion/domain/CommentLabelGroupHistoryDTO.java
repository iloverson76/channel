package com.deepexi.promotion.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelGroupHistoryDTO extends AbstractObject implements Serializable {

    /**
    * 标签组历史主键
    */
    private Long id;
    /**
    * 标签组id
    */
    private Long  labelGroupId;
    /**
    * 标签组名称
    */
    private String labelGroupName;
    /**
    * 历史记录
    */
    private String history;
    /**
    * 应用ID
    */
    private Integer  appId;
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
     * 修改类型 1更新名称 2更新关联 3更新名称+关联
     */
    private Integer updateType;


}

