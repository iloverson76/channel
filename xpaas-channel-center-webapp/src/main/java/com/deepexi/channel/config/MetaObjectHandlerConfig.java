package com.deepexi.channel.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.util.StringUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
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

        //tenantId
        Object tenantId=getFieldValByName("tenantId", metaObject);
        if(null==tenantId){
            if (StringUtil.isEmpty(appRuntimeEnv.getTenantId())){
                setFieldValByName("tenantId", "1ab913906e6f45169557fcd74c2d4984", metaObject);
            }else{
                setFieldValByName("tenantId", appRuntimeEnv.getTenantId(), metaObject);
            }
        }

        //appId
        Object appId=getFieldValByName("appId", metaObject);
        if(null==appId){
            if(StringUtil.isEmpty(appRuntimeEnv.getAppId())){
                setFieldValByName("appId", "104", metaObject);
            } else {
                setFieldValByName("appId", appRuntimeEnv.getAppId(), metaObject);
            }
        }

        //createdTime
        Object createdTime = getFieldValByName("createdTime", metaObject);
        if (null == createdTime){
            setFieldValByName("createdTime", new Date(), metaObject);

        }

        //updatedTime
        Object updatedTime=getFieldValByName("updatedTime", metaObject);
        if(null==updatedTime){
            setFieldValByName("updatedTime", new Date(), metaObject);
        }

        //createdBy
        Object createdBy=getFieldValByName("createdBy", metaObject);
        if(null==createdBy){
            if(StringUtil.isEmpty(appRuntimeEnv.getUsername())){
                setFieldValByName("createdBy", "ANONYMOUS", metaObject);
            }else{
                setFieldValByName("createdBy", appRuntimeEnv.getUsername(), metaObject);
            }
        }

        //updatedBy
        Object updatedBy=getFieldValByName("updatedBy", metaObject);
        if(null==updatedBy){
            if(StringUtil.isEmpty(appRuntimeEnv.getUsername())){
                setFieldValByName("updatedBy", "ANONYMOUS", metaObject);
            }else{
                setFieldValByName("updatedBy", appRuntimeEnv.getUsername(), metaObject);
            }
        }

        //version
        setFieldValByName("version", 1, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("updatedBy", appRuntimeEnv.getUsername(), metaObject);

    }
}

