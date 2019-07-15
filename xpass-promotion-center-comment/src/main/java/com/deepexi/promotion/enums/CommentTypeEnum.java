package com.deepexi.promotion.enums;

/**
 * 评价类型枚举
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 10:47
 */
public enum CommentTypeEnum {

    /**
     * 普通评论
     */
    COMMENT(1),

    /**
     * 追加评论
     */
    ADDITIONAL_COMMENT(2),

    /**
     * 回复
     */
    REPLY_COMMENT(3);


    private final Integer code;


    CommentTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CommentTypeEnum getByCode(Integer code){
        for (CommentTypeEnum typeEnum : values()){
            if (typeEnum.getCode().equals(code)){
                return typeEnum;
            }
        }
        return null;
    }

}
