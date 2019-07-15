package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.promotion.domain.SuperEntity;
import lombok.*;

import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@TableName("comment_star_template_detail")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentStarTemplateDetailDO extends SuperEntity {

    /**
     * 星级评价模板Id
     */
    private Long starTemplateId;

    /**
     * 评分值
     */
    private Integer value;

    /**
     * 支持设置：分值输入，视频上传，文件上传
     */
    private String supportSettings;

    /**
     * 应用Id
     */
    private Long appId;

    /**
     * 模板昵称
     */
    private String nickName;


}
