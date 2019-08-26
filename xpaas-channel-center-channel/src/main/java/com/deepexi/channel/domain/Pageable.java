package com.deepexi.channel.domain;

/**
 * @author zhoust
 * @date 2019/7/19
 **/
public interface Pageable {
    Integer getPage();

    Integer getSize();

    default Boolean isPage(){
        return getPage() != null && getPage() != -1;
    }
}
