package com.deepexi.channel.enums;

import com.deepexi.util.constant.BaseEnumType;
import lombok.Data;

/**
 * Created by donh on 2019/1/8.
 */
public enum CouponStatusEnum implements BaseEnumType{

    NOT_INVALID("0","已过期"),
    INVALID("1","未过期"),

    ACTIVITY_NOT_LINKED("2","未关联活动"),
    ACTIVITY_LINKED("3","已关联活动用户未领用"),

    INITIATIVE_ACCEPTED("4","用户主动领取未使用"),
    INITIATIVE_ACCEPTED_NOT_USED("5","用户主动领取已使用"),

    TRANSFERED("6","用户已转让"),
    ACCEPTED("7","用户受让未使用"),
    ACCEPTED_USERD("8","用户受让已使用"),

    SYSTEM_ASSIGNED("9","系统派发未使用"),
    SYSTEM_ASSIGNED_USED("10","系统派发已使用"),
    ;

    private String code;

    private String msg;

    CouponStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}