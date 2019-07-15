package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelDetailQuery extends AbstractObject implements Serializable {
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
     *标签组ID 批量查条件
     */
    private Long[] ids;

    /**
     * 标签 labelIds
     */
    private Long[] labelIds;





}

