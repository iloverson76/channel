package com.deepexi.channel.extension;

import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.util.IdGenerator;
import com.deepexi.util.extension.ApplicationException;

/**
 * author      : dormi330
 * date        : 2018/6/30
 * project     : mybatis
 * description : 应用运行上下文
 * 遇到过很多这样的场景, 与领域无关的东西, 渗透到领域模型里面去
 * 想通过某种方式解决这个问题,
 */
public class AppRuntimeEnv {

    private static AppRuntimeEnv instance = null;

    private AppRuntimeEnv() {

    }

    public static AppRuntimeEnv getInstance() {
        if (null == instance) {
            synchronized (AppRuntimeEnv.class) {
                if (null == instance) {
                    instance = new AppRuntimeEnv();
                }
            }
        }
        return instance;
    }

    /**
     * 租户编码
     */
    private final static ThreadLocal<String> TENANT_ID = ThreadLocal.withInitial(() -> null);
    /**
     * 应用编码
     */
    private final static ThreadLocal<String> APP_ID = ThreadLocal.withInitial(() -> null);
    /**
     * token信息
     */
    private final static ThreadLocal<String> TOKEN = ThreadLocal.withInitial(() -> null);
    /**
     * 登陆用户名
     */
    private final static ThreadLocal<String> USERNAME = ThreadLocal.withInitial(() -> "ANONYMOUS");

    /**
     * requestId
     */
    private final static ThreadLocal<String> REQUEST_ID = ThreadLocal.withInitial(IdGenerator::getUuId);

    public AppRuntimeEnv ensureToken(String token) throws Exception {
        if (null == token) {
            throw new ApplicationException(ResultEnum.TOKEN_NOT_FOUND);
        }
        TOKEN.set(token);
        return this;
    }

    public AppRuntimeEnv ensureTenantId(String tenantId) throws Exception {
        if (null == tenantId) {
            throw new ApplicationException(ResultEnum.TENANT_NOT_FOUND);
        }
        TENANT_ID.set(tenantId);
        return this;
    }

    public AppRuntimeEnv ensureUsername(String username) throws Exception {
        if (null == username) {
            throw new ApplicationException(ResultEnum.USERNAME_NOT_FOUND);
        }
        USERNAME.set(username);
        return this;
    }

    public void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }

    public void setToken(String token) {
        TOKEN.set(token);
    }

    public String getTenantId() {
        return "123456789";//TENANT_ID.get();
    }

    public String getToken() {
        return TOKEN.get();
    }

    public void setUsername(String username) {
        USERNAME.set(username);
    }

    public String getUsername() {
        return USERNAME.get();
    }

    public String getRequestId() {
        return REQUEST_ID.get();
    }

    public void setAppId(String appId) {
        APP_ID.set(appId);
    }

    public String getAppId() {
        return "123456789";//APP_ID.get();
    }
}