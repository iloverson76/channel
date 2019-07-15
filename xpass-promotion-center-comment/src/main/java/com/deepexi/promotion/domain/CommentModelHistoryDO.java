package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@TableName("comment_model_history")
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentModelHistoryDO extends SuperEntity {

    /**
     * 应用id
     */
    private Long  appId;
    /**
     * 评价对象的id
     */
    private Long  modelId;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private  String code;
    /**
     * 标识码
     */
    private String identificationCode;
    /**
     * 版本号，乐观锁
     */
    private Integer  version;
    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    private String remark;

    /**
     * 更新类型
     */
    private Integer updateType;



}

