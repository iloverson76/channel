package com.deepexi.promotion.domain;

import java.util.Date;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentModelHistoryVO extends AbstractObject {

    /**
     * 评价历史主键
     */
    private Long  id;
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
    private String  code;

    /**
     * 标识码
     */
    private String identificationCode;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private String createdBy;


    /**
     * 多租户标识
     */
    private String tenantId;

    /**
     * 更新类型
     */
    private Integer updateType;

}

