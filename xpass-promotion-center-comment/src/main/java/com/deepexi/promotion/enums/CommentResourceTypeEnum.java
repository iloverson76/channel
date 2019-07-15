package com.deepexi.promotion.enums;

/**
 * 评论资源类型
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 10:52
 */
public enum CommentResourceTypeEnum {

    /**
     * text 文本
     */
    TEXT(1),

    /**
     * 图片
     */
    IMAGE(2),

    /**
     * 视频
     */
    VIDEO(3);


    private final Integer code;


    CommentResourceTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CommentResourceTypeEnum of(Integer code) {
        for (CommentResourceTypeEnum item : CommentResourceTypeEnum.values()) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal argument ：" + code);
    }

}
