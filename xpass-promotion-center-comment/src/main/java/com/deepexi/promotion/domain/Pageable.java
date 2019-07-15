package com.deepexi.promotion.domain;

/**
 * @author zhoust
 * @date 2019/5/17
 **/
public interface Pageable {

    Integer getPage();

    Integer getSize();

    default Boolean isPage(){
        return getPage() != null && getPage() != -1;
    }
}
