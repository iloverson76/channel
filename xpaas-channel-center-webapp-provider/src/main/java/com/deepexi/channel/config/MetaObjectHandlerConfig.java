package com.deepexi.channel.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.util.StringUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    // mybatis-plus公共字段自动填充，https://baomidou.oschina.io/mybatis-plus-doc/#/auto-fill
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createdTime", new Date(), metaObject);
        setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("tenantId", appRuntimeEnv.getTenantId(), metaObject);
        if(StringUtil.isEmpty(appRuntimeEnv.getUsername())){
            setFieldValByName("createdBy", "ANONYMOUS", metaObject);
            setFieldValByName("updatedBy", "ANONYMOUS", metaObject);
        }else{
            setFieldValByName("createdBy", appRuntimeEnv.getUsername(), metaObject);
            setFieldValByName("updatedBy", appRuntimeEnv.getUsername(), metaObject);
        }
        setFieldValByName("version", 1, metaObject);

        if(StringUtil.isEmpty(appRuntimeEnv.getAppId())){
            setFieldValByName("appId", "123456789", metaObject);
        } else {
            setFieldValByName("appId", appRuntimeEnv.getAppId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("updatedBy", appRuntimeEnv.getUsername(), metaObject);

    }
}