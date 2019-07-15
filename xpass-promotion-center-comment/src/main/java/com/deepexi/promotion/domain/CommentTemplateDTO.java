package com.deepexi.promotion.domain;


import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;


/**
 * 模板返回结果集
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentTemplateDTO extends AbstractObject {
    /**
     * 业务对象关联主键
     */
    private Long id;
    /**
     * 评价对象主键
     */
    private Long modelId;
    /**
     * 业务ID
     */
    private Long businessId;
    /**
     * 应用ID
     */
    private Long appId;
    /**
     * 评价对象名称
     */
    private String modelName;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 是否包含有回评模板
     */
    private Integer isReply;
    /**
     * 文本设置
     */
    private CommentBusinessTextSetDTO commentBusinessTextSetVO;

    /**
     * 标签组
     */
    private List<CommentLabelGroupDTO> commentBusinessLabelGroupSetVO;

    private List<CommentStarTemplateDTO> commentBusinessStarSetVO;
}

