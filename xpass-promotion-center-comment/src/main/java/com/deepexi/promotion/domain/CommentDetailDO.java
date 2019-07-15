package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.*;


/**
 * 评价明细表
 *
 * @author LXX
 */
@EqualsAndHashCode(callSuper = true)
@TableName("comment_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDetailDO extends SuperEntity {

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 系统业务id
     */
    private Long appBusinessId;

    /**
     * 评价id
     */
    private Long commentId;

    /**
     * 评价对象id
     */
    private Long modelId;

    /**
     * 评价对象名称
     */
    private String modelName;

    /**
     * 目标id
     */
    private String targetId;

    /**
     * 目标名称
     */
    private String targetName;

    /**
     * 评价类型（1：普通，2：追评，3：回复)
     */
    private Integer commentType;

    /**
     * 评价渠道 1：H5,2：iOS，3：Android
     */
    private Integer channel;

    /**
     * 评价用户id
     */
    private String userId;

    /**
     * 审核状态（1：未审核，2：已审核，3：不通过）
     */
    private Integer checkStatus;

    /**
     * 父评价id（追评和回复有此字段）
     */
    private Long parentId;

    /**
     * 用于根据parentId查询出回复和追评记录
     */
    @TableField(exist = false)
    private Set<CommentDetailDO> children;

    /**
     * 回复状态 0:未回复 1:已回复
     */
    private Integer replyStatus;


    /**
     * tree 转 List
     * queue实现广度遍历
     *
     * @param node CommentDetailDO
     * @return List<CommentDetailDO>
     */
    public static List<CommentDetailDO> treeToList(CommentDetailDO node) {
        List<CommentDetailDO> trees = new ArrayList<>();
        LinkedList<CommentDetailDO> result = new LinkedList<>();
        result.add(node);
        while (!result.isEmpty()) {
            CommentDetailDO node1 = result.poll();
            trees.add(node1);
            LinkedHashSet<CommentDetailDO> set = new LinkedHashSet<>(node1.getChildren());
            if (set.size() > 0) {
                for (int i = 0; i < set.size(); i++) {
                    CommentDetailDO treeNode = (CommentDetailDO) set.toArray()[i];
                    result.add(treeNode);
                }
            }
        }
        return trees;
    }

    /**
     * treeList 转 List
     * queue实现广度遍历
     *
     * @param nodes List<CommentDetailDO>
     * @return List<CommentDetailDO>
     */
    public static List<CommentDetailDO> treeListToList(List<CommentDetailDO> nodes) {
        List<CommentDetailDO> trees = new ArrayList<>();
        for (CommentDetailDO node : nodes) {
            trees.addAll(treeToList(node));
        }
        return trees;
    }

}

