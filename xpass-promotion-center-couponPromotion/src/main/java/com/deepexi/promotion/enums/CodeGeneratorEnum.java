package com.deepexi.promotion.enums;

import java.util.function.Function;

/**
 * 编码code统一生成
 * @author zhoust
 * @date 2019/5/23
 **/
public enum CodeGeneratorEnum {
    STAR_TEMPLATE_CODE("comment_star_template",x -> String.valueOf(1000 + x)),
    LABEL_TEMPLATE_CODE("comment_label_template",x -> String.valueOf(1000 + x));

    private final String tableName;
    private final Function<Long,String> codeGetter;

    CodeGeneratorEnum(String tableName, Function<Long, String> codeGetter) {
        this.tableName = tableName;
        this.codeGetter = codeGetter;
    }

    public Long getLongCode(Long id){
        return Long.valueOf(codeGetter.apply(id));
    }

    public String getStringCode(Long id){
        return codeGetter.apply(id);
    }
}
