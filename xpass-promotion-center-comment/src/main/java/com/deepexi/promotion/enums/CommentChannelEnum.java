package com.deepexi.promotion.enums;

/**
 * 评价渠道枚举
 * <P>
 *     这个枚举本身应该由应用层定义，但是一期因为有后台管理，这里作为最佳实现定义一个
 * </P>
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 10:43
 */
public enum  CommentChannelEnum {

    /**
     * PC H5端
     */
    H5(1),

    /**
     * 苹果移动端
     */
    IOS(2),

    /**
     * 安卓手机端
     */
    ANDROID(3);


    private final Integer code;


    CommentChannelEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
