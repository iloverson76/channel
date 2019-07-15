package com.deepexi.promotion.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务主题枚举
 *
 * @author zhoust
 * @date 2019/5/31
 **/
public enum BusinessTopicEnum {
    APP("app"),
    COMMENT("promotion");

    private final String code;

    BusinessTopicEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BusinessTopicEnum getByCode(String code){
        if (StringUtils.isEmpty(code)){
            return null;
        }
        for (BusinessTopicEnum topicEnum : values()){
            if (topicEnum.getCode().equals(code)){
                return topicEnum;
            }
        }
        return null;
    }
}
