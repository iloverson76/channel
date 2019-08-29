package com.deepexi.channel.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.deepexi.channel.extension.AppRuntimeEnv;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Slf4j
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    private static final String UPDATED_TIME = "updatedTime";

    private static final String UPDATED_BY = "updatedBy";

    private static final String CREATED_TIME = "createdTime";

    private static final String CREATED_BY = "createdBy";

    private static final String TENANT_ID = "tenantId";

    private static final String APP_ID = "appId";

    AppRuntimeEnv appRuntimeEnv= AppRuntimeEnv.getInstance();

    /**
     *  mybatis-plus公共字段自动填充，https://baomidou.oschina.io/mybatis-plus-doc/#/auto-fill
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (getFieldValByName(CREATED_TIME, metaObject) == null) {
                Date now = new Date();
                setFieldValByName(CREATED_TIME, now, metaObject);
                setFieldValByName(UPDATED_TIME, now, metaObject);
            }
            if (getFieldValByName(TENANT_ID, metaObject) == null) {
                setFieldValByName(TENANT_ID, appRuntimeEnv.getTenantId(), metaObject);
            }
            if (getFieldValByName(CREATED_BY, metaObject) == null) {
                setFieldValByName(CREATED_BY, appRuntimeEnv.getUsername(), metaObject);
                setFieldValByName(UPDATED_BY, appRuntimeEnv.getUsername(), metaObject);
            }
            if (getFieldValByName(APP_ID,metaObject) == null){
                setFieldValByName(APP_ID, appRuntimeEnv.getAppId(), metaObject);
            }
        } catch (Exception e) {
            log.error("自动注入创建人或者租户id失败：{}", e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (getFieldValByName(UPDATED_TIME, metaObject) == null) {
                setFieldValByName(UPDATED_TIME, new Date(), metaObject);
            }
            if (getFieldValByName(UPDATED_BY, metaObject) == null) {
                setFieldValByName(UPDATED_BY, appRuntimeEnv.getUsername(), metaObject);
            }
        } catch (Exception e) {
            log.error("自动注入更新人失败：{}", e.getMessage());
        }
    }
}