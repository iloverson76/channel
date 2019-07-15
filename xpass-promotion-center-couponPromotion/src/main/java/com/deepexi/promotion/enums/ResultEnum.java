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
    STAR_TEMPLATE_NOT_EXIST("200005","星级评价模板不存在"),
    STAR_TEMPLATE_NAME_EXIST("200005","星级评价名称不可重复"),
    IDENTIFICATIONCODE_EXIST("400","标识码不可重复"),
    BUSINESS_NOT_EXIST("400","业务不存在"),
    OBJECT_EXIST("200004", "对象已存在"),
    LABEL_TEMPLATE_EXIST("200006","标签已存在"),
    JSON_PARSE_ERROR("200005", "JSON格式错误"),
    ADDITIONAL_COMMENT_NOT_ALLOW_DELETE("200008", "追加的评论不能直接删除，请直接删除原评论！"),
    RECORD_NOT_FOUND("400", "记录没有找到"),

    CREATE_ERROR("400", "创建对象失败"),
    NAME_DUPLICATE("400","名称重复"),
    STAR_TEMPLATE_DETAIL_NOT_FOUND("400", "星级评价明细不存在"),
    STATISTIC_TEMPLATE_NOT_FOUND("400", "获取不到统计模板类"),
    STATISTIC_QUERY_STRATEGY_NOT_FOUND("400", "获取不到统计分类查询策略,请检查参数type"),

    PARAM_ERR("400","参数不正确"),
    COMMENT_TEMPLATE_NOT_EXIST("400","该对象不存在评价模板"),
    REPLY_TEMPLATE_EXIST("400","该对象已存在回评模板"),
    COMMENT_TEMPLATE_EXIST("400","该对象已存在评价模板"),
    USER_INCONSISTENCY("400","评价用户和追评用户不一致"),

    START_TIME_GREATERTHAN_END_TIME("400","起始时间大于结束时间"),
    PARAM_MISS_ID_AND_CODE("400","id和code不能同时为空"),
    COMMENT_DETAIL_IS_NULL("400","评价内容为空"),
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