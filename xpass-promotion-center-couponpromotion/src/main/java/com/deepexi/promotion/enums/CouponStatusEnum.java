package com.deepexi.promotion.enums;

import com.deepexi.util.constant.BaseEnumType;
import lombok.Data;

/**
 * Created by donh on 2019/1/8.
 */
public enum CouponStatusEnum implements BaseEnumType{


  /*  START_TIME_GREATERTHAN_END_TIME("400","起始时间大于结束时间"),
    PARAM_MISS_ID_AND_CODE("400","id和code不能同时为空"),
    COMMENT_DETAIL_IS_NULL("400","评价内容为空"),
    ;*/

    NOT_LINK_ACTIVITY("0","未关联活动"),
    LINK_ACTIVITY("1","已关联活动"),//未派发给用户

    //INITIATIVE_ACCEPTED_NOT_USED("2","主动领取-未使用"),
    //INITIATIVE_ACCEPTED_USED("3","主动领取-已使用"),


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