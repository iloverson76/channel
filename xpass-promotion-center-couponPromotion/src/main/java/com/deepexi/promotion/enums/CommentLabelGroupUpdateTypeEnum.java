package com.deepexi.promotion.enums;

public enum CommentLabelGroupUpdateTypeEnum {
    NAME_CHANGE(1,"名称更改"),
    CONNECT_CHANGE(2,"关联改变"),
    NAME_CONNECT_CHANGE(3,"信息改变")
    ;

    private Integer code;

    private String msg;

    CommentLabelGroupUpdateTypeEnum(Integer code, String msg) {
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
