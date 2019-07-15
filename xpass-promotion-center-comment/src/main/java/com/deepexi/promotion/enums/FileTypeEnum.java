package com.deepexi.promotion.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhoust
 * @date 2019/5/31
 **/
public enum  FileTypeEnum {

    IMG("img"),
    VIDEO("video"),
    FILE("file");

    private final String code;

    FileTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static FileTypeEnum getBycode(String code){
        if (StringUtils.isEmpty(code)){
            return null;
        }
        for (FileTypeEnum fileTypeEnum : values()){
            if (fileTypeEnum.getCode().equals(code)){
                return fileTypeEnum;
            }
        }
        return null;
    }
}
