package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelDetailDTO extends AbstractObject implements Serializable {
    /**
     *标签id
     */
   private Long labelTemplateId;
    /**
     * 标签名
     */
   private String labelName;
    /**
     * code
     */
   private String code;
    /**
     * 标签组ID
     */
    private Long labelGroupId;
    /**
     * 关联表ID
     */
    private Long connectId;


}

