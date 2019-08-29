package com.deepexi.channel.enums;

import com.deepexi.util.constant.BaseEnumType;
import io.swagger.models.auth.In;

/**
 * Created by donh on 2019/1/8.
 */
public enum DistributorTypeEnum{

    DISTRIBUTOR(1,"经销商"),
    FACTORY(2,"厂商"),
    ;

    private Integer code;

    private String msg;

    DistributorTypeEnum(Integer code, String msg) {
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