package com.deepexi.promotion.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelGroupVO extends AbstractObject implements Serializable {

    /**
     * 标签组主键
     */
    private Long  id;

    /**
     * 标签组名称
     */
    private String name;

    /**
     * 包含标签详细
     */
    private List<CommentLabelDetailVO> labelList;

}

