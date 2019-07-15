package com.deepexi.promotion.domain;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("comment_label_group_history")
public class CommentLabelGroupHistoryDO extends SuperEntity implements Serializable {

    /**
     * 标签组id
     */
    // @ApiModelProperty(value = "标签组id")
    private Long labelGroupId;
    /**
     * 标签组名称
     */
    // @ApiModelProperty(value = "标签组名称")
    private String labelGroupName;
    /**
     * 历史记录
     */
    // @ApiModelProperty(value = "历史记录")
    private String history;
    /**
     * 应用ID
     */
    // @ApiModelProperty(value = "应用ID")
    private Long appId;
    /**
     * 版本号，乐观锁
     */
    // @ApiModelProperty(value = "版本号，乐观锁")
    private Integer  version;
    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    // @ApiModelProperty(value = "备注，一些必要备注，如删除时，为什么删除")
    private String remark;

    /**
     * 修改类型 1更新名称 2更新关联 3更新名称+关联
     */
    private Integer updateType;

}

