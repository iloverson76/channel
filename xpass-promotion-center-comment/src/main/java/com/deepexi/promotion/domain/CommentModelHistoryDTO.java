package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentModelHistoryDTO extends AbstractObject {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 应用id
     */
    private Long appId;
    /**
     * 评价对象的id
     */
    private Long modelId;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 标识码
     */
    private String identificationCode;
    /**
     * 版本号，乐观锁
     */
    private Integer version;
    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    private String remark;

    /**
     * 更新类型
     */
    private Integer updateType;

    /**
     * 删除状态 0无效 1有效
     */
    private Boolean dr;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改时间
     */
    private Date updatedTime;
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
}

