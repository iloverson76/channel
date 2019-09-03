package com.deepexi.channel.enums;

/**
 * Created by donh on 2019/1/8.
 */
public enum LimitedEnum {

    UNLIMITED(0,"不限制上级"),
    LIMITED(1,"限制上级"),
    ;

    private Integer code;

    private String msg;

    LimitedEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}