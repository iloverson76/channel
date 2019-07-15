package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelGroupInputDTO extends AbstractObject implements Serializable {


    /**
    * 标签组名称
    */
    private String name;
    /**
    * 应用id
    */
    private Long appId;

    /**
     * 标签组id
     */
    private List<Long> labelIdsList;

}

