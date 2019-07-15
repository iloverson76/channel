package com.deepexi.promotion.enums;

/**
 * 是否有评论模板
 *
 * @author liaoxiaoxin
 * @date 2019-06-04 10:47
 */
public enum BusinessModelConnectStatusEnum {

    /**
     * 不允许评论
     */
    COMMENT_DISABLE(0),

    /**
     * 允许评论
     */
    COMMENT_ENABLE(1);

    private final Integer code;


    BusinessModelConnectStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
