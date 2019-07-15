package com.deepexi.promotion.enums;


/**
 * @Classname CommentMdelCodeEnum
 * @Description  评价对象历史-更改类型枚举
 * @Date 2019/5/9 11:24
 * @Created by zhangyanping
 */
public enum CommentMdelCodeEnum{
    NAME_CHANGE(1,"名称更改"),
    IDENTI_CODE_CHANGE(2,"标识码改变"),
    NAME_CODE_CHANGE(3,"名称，标识码改变")
    ;

    private Integer code;

    private String msg;

    CommentMdelCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
