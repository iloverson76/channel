package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhoust
 * @date 2019/5/17
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelTemplateHistoryVO extends AbstractObject {
    private String name;

    private Integer updateType;

    private Date updateTime;
}
