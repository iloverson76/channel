package com.deepexi.channel.enums;

import com.deepexi.util.constant.BaseEnumType;

/**
 * Created by donh on 2019/1/8.
 */
public enum ResultEnum implements BaseEnumType {
    RPC_ERROR("300000", "RPC调用异常!请检查服务是否正常!!!"),

    UNKNOWN_ERROR("500", "系统出异常啦!请联系管理员!!!"),

    NON_SUPPORT("500", "不支持的操作"),
    FILE_UPLOAD_ERROR("400", "文件上传异常"),
    SUCCESS("200", "success"),
    USER_EXIST("100002", "用户已存在！"),
    NETWORK_LIMIT("100001", "网络限流！"),
    TOKEN_NOT_FOUND("200001", "token不能为空！"),
    TENANT_NOT_FOUND("200002", "tenantId不能为空！"),
    USERNAME_NOT_FOUND("200003", "username不能为空！"),

    APPNAME_EXIST("400", "应用名不可重复"),
    APP_NOT_EXIST("400", "系统应用不存在"),
    FILE_EXT_NON_SUPPORT("400", "不支持的文件类型"),
    UPLOAD_FILE_FAIL("400", "上传文件失败"),
    UPLOAD_FILE_TOO_BIG("400", "上传文件太大"),

    JSON_PARSE_ERROR("200005", "JSON格式错误"),

    NAME_DUPLICATE("400", "名称重复"),
    PARAM_ERR("400", "参数不正确"),
    PARAM_MISS("400", "参数缺失"),
    TYPE_NAME_DUPLICATE("400", "类型名称重复"),
    TYPE_CODE_DUPLICATE("400", "类型编码重复"),
    LENGTH_TOO_LONG("400", "长度超过最大值"),
    RULE_EXIST("400", "规则已存在"),
    RULE_PARAMETER_CODE_EXIST("400", "规则参数字段不可重复"),
    ACTIVITY_EXIST("400", "活动已存在"),
    ACTIVITY_NOT_EXIST("400", "活动不存在"),
    ACTIVITY_CANNOT_EXIT("400", "活动信息不可编辑"),
    ACTIVITY_ID_NOT_NULL("400", "活动id不可为空"),
    APP_ID_OR_TENANT_ID_NULL("400", "tenantId和appId不能为空"),
    TEMPLATE_INSERT_FAIL("400", "模板插入失败"),
    TEMPLATE_CODE_REPEAT("400", "模板编码重复"),
    TEMPLATE_RULE_INSTANCE_INSERT_FAIL("400", "模板规则实例插入失败"),
    TEMPLATE_UPDATE_FAIL("400", "模板更新失败"),
    IDS_NOT_EMPTY("400", "ids不能为空"),

    TYPE_IS_USEING("400", "类型正在使用中，不能删除"),
    TEMPLATE_NOT_EXITS("400", "模板信息不存在"),
    TEMPLATE_IS_PUBLISH("400", "模板已经发布，不能删除"),

    API_CALL_RESULT_IS_NULL("400", "API调用结果为null"),
    API_RESULT_TYPE_IS_ERROR("400", "API调用结果类型错误"),
    ID_DO_NOT_NULL("400", "id不能为空"),

    AREA_TYPE_NULL("400","数据出错,没有对应的区域类型"),
    CODE_NOT_UNIQUE("400","编码重复"),
    HAVE_CHILDREN("400","具有子节点，不能删除"),
    HAVE_RELATION("400","已被关联，不能删除"),
    AREA_TYPE_DUPLICATED("400","区域编码不能重复"),
    GRADE_SYSTEM_CODE_DUPLICATED("400","体系编码不能重复"),
    GRADE_SYSTEM_NAME_DUPLICATED("400","体系名称不能重复"),
    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}