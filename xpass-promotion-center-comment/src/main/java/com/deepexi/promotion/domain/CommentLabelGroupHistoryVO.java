package com.deepexi.promotion.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelGroupHistoryVO extends AbstractObject implements Serializable {

    /**
     * 标签组历史主键
     */
    private Long  id;


    /**
     * 标签组名称
     */
    private String labelGroupName;

    /**
     * 历史记录
     */
    private String history;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updatedTime;


    /**
     * 修改类型 1更新名称 2更新关联 3更新名称+关联
     */
    private Integer updateType;



}

