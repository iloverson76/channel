package com.deepexi.promotion.enums;

public enum CommentTemplateTypeEnum {

    /**
     * 评价类型模板
     */
    COMMENT_TEMPLATE(0),

    /**
     * 回复类型模板
     */
    REPLY_TEMPLATE(1);

    private final Integer code;


    CommentTemplateTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
