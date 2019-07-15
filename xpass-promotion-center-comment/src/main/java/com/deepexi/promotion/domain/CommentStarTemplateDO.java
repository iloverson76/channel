package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.promotion.domain.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@TableName("comment_star_template")
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarTemplateDO  extends SuperEntity {

    /**
     * 名称
     */
    private String name;
    /**
     * 编号
     */
    private String code;

    /**
     * 应用Id
     */
    private Long appId;

    /**
     * 评分最高值
     */
    private Integer  maxValue;


    /**
     * 是否开启
     */
    private Boolean enableGlobalInputBox;


}
