package com.deepexi.promotion.enums;

import com.deepexi.util.constant.BaseEnumType;

/**
 * Created by donh on 2019/1/8.
 */
public enum ResultEnum implements BaseEnumType{
    RPC_ERROR("300000", "RPC调用异常!请检查服务是否正常!!!"),

    UNKNOWN_ERROR("500", "系统出异常啦!请联系管理员!!!"),
    FILE_UPLOAD_ERROR("400", "文件上传异常"),
    SUCCESS("200", "success"),
    USER_EXIST("100002", "用户已存在！"),
    NETWORK_LIMIT("100001", "网络限流！"),
    TOKEN_NOT_FOUND("200001", "token不能为空！"),
    TENANT_NOT_FOUND("200002", "tenantId不能为空！"),
    USERNAME_NOT_FOUND("200003", "username不能为空！"),

    APPNAME_EXIST("400","应用名不可重复"),
    APP_NOT_EXIST("400", "系统应用不存在"),
    FILE_EXT_NON_SUPPORT("400", "不支持的文件类型"),
    UPLOAD_FILE_FAIL("400","上传文件失败"),
    UPLOAD_FILE_TOO_BIG("400","上传文件太大"),

    JSON_PARSE_ERROR("200005", "JSON格式错误"),

    NAME_DUPLICATE("400","名称重复"),
    PARAM_ERR("400","参数不正确")
    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
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