package com.deepexi.promotion.utils;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.cache.api.ICacheService;

import com.deepexi.promotion.common.RandomGenerator;
import com.deepexi.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;


@Slf4j
public class RedisUtils {


    private static ICacheService cacheService = SpringUtil.getBean(ICacheService.class);


    /**
     * 随机
     */
    private static final Random RANDOM = new Random();


    private static final int EXPIRE_TIME = 1800;

    private static final int BOUND = 1200;

    private static final int REQUEST_ID_LENGTH = 10;

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();


    /**
     * * 批量删除
     *
     * @param prefix key前缀
     * @param ids    id集合
     */
    public static void deleteCache(String prefix, Long... ids) {

        if (null == ids || ids.length == 0) {
            return;
        }
        deleteCache(prefix, Arrays.asList(ids));

    }

    public static void deleteCache(String prefix, List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        try {
            List<String> idList = ids.stream().filter(Objects::nonNull).map(id -> StringUtils.join(prefix, id.toString())).collect(Collectors.toList());
            idList.forEach(key -> {
                cacheService.delCache(key);
            });
        } catch (Exception e) {
            log.error("缓存淘汰异常.", e);
        }
    }

    public static void deleteCache(List<String> keyList) {
        if (CollectionUtil.isEmpty(keyList)) {
            return;
        }
        try {
            for (String key : keyList) {
                cacheService.delCache(key);
            }
        } catch (Exception e) {
            log.error("缓存淘汰异常.", e);
        }
    }

    public static void deleteCache(String key) {
        cacheService.delCache(key);
    }

    /**
     * 缓存设值
     *
     * @param key key
     * @param o   值
     */
    public static void setCache(String key, Object o) {

        if (StringUtils.isBlank(key) || null == o) {
            return;
        }
        cacheService.setCache(key, o);
    }

    /**
     * 设置缓存和失效时间
     *
     * @param key key
     * @param o   值
     */
    public static void setCacheAndExpTime(String key, Object o) {
        if (StringUtils.isEmpty(key) || null == o) {
            return;
        }
        cacheService.setCache(key, JSONObject.toJSONString(o), RANDOM.nextInt(BOUND) + EXPIRE_TIME);
    }

    public static String getCache(String prefix, String key) {
        return getCache(StringUtils.join(prefix, key), String.class);
    }

    public static String getCache(String key) {
        return getCache(key, String.class);
    }

    public static <T> T getCache(String key, Class<T> clz) {
        return cacheService.getCache(key, clz);
    }

    /**
     * 上锁
     *
     * @param key key
     * @return
     */
    public static boolean lock(String key) {
        String requestId = RandomGenerator.getNonce_str(REQUEST_ID_LENGTH);
        THREAD_LOCAL.set(requestId);
        return cacheService.tryLock(key, requestId);
    }

    /**
     * @param key  key
     * @param time 单位毫秒
     * @return
     */
    public static boolean lockAndSetExpTime(String key, int time) {
        String requestId = RandomGenerator.getNonce_str(REQUEST_ID_LENGTH);
        return cacheService.tryLock(key, requestId, time);
    }


    /**
     * 释放锁
     *
     * @param key key
     * @return
     */
    public static boolean releaseLock(String key) {
        String requestId = THREAD_LOCAL.get();
        boolean flag = cacheService.releaseLock(key, requestId);
        THREAD_LOCAL.remove();
        return flag;
    }
}
