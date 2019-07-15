package com.deepexi.promotion.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("comment_label_group_connect")
public class CommentLabelGroupConnectDO extends SuperEntity implements Serializable {

    /**
     * 标签组id
     */
    // @ApiModelProperty(value = "标签组id")
    private Long labelGroupId;
    /**
     * 标签id
     */
    // @ApiModelProperty(value = "标签id")
    private Long labelTemplateId;
    /**
     * 应用id
     */
    // @ApiModelProperty(value = "应用id")
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



}

