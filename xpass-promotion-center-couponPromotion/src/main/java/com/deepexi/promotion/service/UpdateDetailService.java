package com.deepexi.promotion.service;

/**
 * @author zhoust
 * @date 2019/5/20
 **/
public interface UpdateDetailService<T> {

    String SEPARATOR = ",";

    String getUpdateCodesDetail(T update, T old);

    String getUpdateCnNamesByCodes(String codes);
}
