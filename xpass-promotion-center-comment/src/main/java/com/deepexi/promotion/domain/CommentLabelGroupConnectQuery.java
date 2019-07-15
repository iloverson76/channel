package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;

import javax.validation.constraints.Min;


/**
 * @author
 */
@Data
public class CommentLabelGroupConnectQuery extends AbstractObject {

    /**
     * 标签和组关联主键
     */
    private Long id;
    /**
     * 标签组id
     */
    private Long labelGroupId;
    /**
     * 标签id
     */
    private Long labelTemplateId;
    /**
     * 应用id
     */
    private Long appId;
    /**
     * 当前页码
     */
    @Min(value = -1,message = "页码参数超范围")
    private Integer page;
    /**
     * 分页大小
     */
    @Min(value = 0,message = "分页大小超范围")
    private Integer size;
}

