package com.deepexi.channel.config;

import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.util.extension.ApplicationException;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/***
 * @ClassName: MybatisPlusConfig
 * @Description: 多租户追加查询实现 ，参考https://mp.baomidou.com/guide/tenant.html
 * @author: chenling
 * @Date: 2019/5/8 14:19
 * @version : V1.0.0
 */
@Configuration
@Slf4j
@MapperScan("com.deepexi.commodity.mapper")
public class MybatisPlusConfig {

    private static final String SYSTEM_TENANT_ID = "tenantId";
    private static final String SYSTEM_APP_ID = "appId";

    private static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("tc_tenant");


    @Value("${mybatis-plus.configuration.map-underscore-to-camel-case}")
    private String flag;


    @Bean
    public PaginationInterceptor paginationInterceptor(AppRuntimeEnv appRuntimeEnv) {

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        TenantSqlParser tenantSqlParser = new TenantSqlParserConfig();
        TenantSqlParser tenantSqlParserAppId = new TenantSqlParserConfig();

        // SQL解析处理拦截：增加租户处理回调。
        tenantSqlParser.setTenantHandler(new TenantHandler() {

            @Override
            public Expression getTenantId() {
                String tenantId = appRuntimeEnv.getTenantId();
                if (null == tenantId) {
                    throw new ApplicationException(ResultEnum.GET_CURRENT_TENANT_ERROR);
                }
                return new StringValue(tenantId);
            }

            @Override
            public String getTenantIdColumn() {
                log.info("\n是否将租户字段转换成为驼峰格式：{}", flag);
                if (Boolean.parseBoolean(flag)) {
                    return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, SYSTEM_TENANT_ID);
                } else {
                    return SYSTEM_TENANT_ID;
                }
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 忽略掉一些表：如租户表（tc_tenant）本身不需要执行这样的处理。
                return IGNORE_TENANT_TABLES.stream().anyMatch(e -> e.equalsIgnoreCase(tableName));
            }
        });
        tenantSqlParserAppId.setTenantHandler(new TenantHandler() {

            @Override
            public Expression getTenantId() {
                String appId = appRuntimeEnv.getAppId();
                if (null == appId) {
                    throw new ApplicationException(ResultEnum.GET_CURRENT_APP_ERROR);
                }
                return new StringValue(appId);
            }

            @Override
            public String getTenantIdColumn() {
                log.info("\n是否将租户字段转换成为驼峰格式：{}", flag);
                if (Boolean.parseBoolean(flag)) {
                    return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, SYSTEM_APP_ID);
                } else {
                    return SYSTEM_APP_ID;
                }
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 忽略掉一些表：如租户表（tc_tenant）本身不需要执行这样的处理。
                return IGNORE_TENANT_TABLES.stream().anyMatch(e -> e.equalsIgnoreCase(tableName));
            }
        });
        ArrayList list = new ArrayList();
        list.add(tenantSqlParser);
        list.add(tenantSqlParserAppId);
        paginationInterceptor.setSqlParserList(list);
        return paginationInterceptor;

    }


    /**
     * @param paginationInterceptor
     * @return void
     * @author chenling
     * @description 过滤自定义查询此时无租户信息，如有需要自行扩展
     * @date 17:09 2019/5/8
     **/
    private void addSqlFilter(PaginationInterceptor paginationInterceptor) {
        paginationInterceptor.setSqlParserFilter(metaObject -> {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);

            List<String> mapperMethod = new ArrayList<>(3);
            //如果需要可在增加
            String methodId = ms.getId();
            mapperMethod.add("com.deepexi.commodity.mapper.GoodsMapper.deleteByIds");
            return mapperMethod.contains(methodId);
        });

    }


//    /**
//     * 这里全部使用mybatis-autoconfigure 已经自动加载的资源。不手动指定
//     * 配置文件和mybatis-boot的配置文件同步
//     *
//     * @return
//     */
//    @Bean
//    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
//
//        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
//        GlobalConfig congig = new GlobalConfig();
//        congig.setMetaObjectHandler(metaObjectHandlerConfig);
//        mybatisPlus.setGlobalConfig(congig);
//        return mybatisPlus;
//    }
}