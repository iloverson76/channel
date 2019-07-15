package com.deepexi.promotion.enums;

/**
 * 评价审核状态枚举
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-12 21:22
 */
public enum  CommentCheckStatusEnum {

    /**
     * 未审核
     */
    UNCHECKED(1),

    /**
     * 已审核
     */
    CHECKED(2),

    /**
     * 审核不通过
     */
    FAIL(3);

    private final Integer code;


    CommentCheckStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
