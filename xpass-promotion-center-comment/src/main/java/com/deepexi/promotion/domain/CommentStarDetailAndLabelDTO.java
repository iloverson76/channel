package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentStarDetailAndLabelDTO extends AbstractObject {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 星级评价模板id
     */
    private Long starTemplateId;

    /**
     * 评价明细分值别名
     */
    private String nickName;

    /**
     * 评价明细分值
     */
    private Integer value;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 评价标签
     */
    private List<CommentLabelTemplateDTO> labels;

    /**
     * 评价设置
     */
    private CommentStarTemplateDetailSupportSettingsDTO supportSettings;




}
