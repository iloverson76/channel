package com.deepexi.promotion.enums;

/**
 * 评价资源，评价标签表关联类型枚举
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 10:56
 */
public enum CommentRelevanceTypeEnum {

    /**
     * 关联评价明细
     */
    COMMENT_DETAIL(1),

    /**
     * 关联星评评价
     */
    COMMENT_STAR(2);


    private final Integer code;


    CommentRelevanceTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
