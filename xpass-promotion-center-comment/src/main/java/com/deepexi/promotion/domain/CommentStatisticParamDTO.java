package com.deepexi.promotion.domain;

import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用于生成评论统计数据的参数类
 *
 * @author liaoxiaoxin
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CommentStatisticParamDTO extends AbstractObject {

    public CommentStatisticParamDTO() {
        this.commentStarList = CollectionUtil.createArrayList();
        this.commentResourceList = CollectionUtil.createArrayList();
        this.commentLabelList = CollectionUtil.createArrayList();
    }

    /**
     * 评价明细ID
     */
    private Long id;

    /**
     * 系统业务id
     */
    private Long appBusinessId;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 关联评价对象id
     */
    private Long modelId;

    /**
     * 统计描述
     */
    private String statisticDesc;

    /**
     * 统计的目标id，比如统计商品，则为商品id
     */
    private String targetId;

    /**
     * 评论类型：{@link com.deepexi.promotion.enums.CommentTypeEnum}
     */
    private Integer commentType;

    /**
     * 评价资源
     */
    private List<CommentResourceInputDTO> commentResourceList;

    /**
     * 星评评价
     */
    private List<CommentStarInputDTO> commentStarList;

    /**
     * 评价标签
     */
    private List<CommentLabelInputDTO> commentLabelList;


}

