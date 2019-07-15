package com.deepexi.promotion.enums;

import java.util.Optional;

/**
 * 统计类型
 * <p>
 *      1：追评，2：回复:3：图片，4：视频，5:星评值 6：评论数量
 *      后续需要扩展，数据库字段只支持256种，0-255
 * </p>
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 22:36
 */
public enum CommentStatisticTypeEnum {

    /**
     * 统计评论数量
     */
    COMMENT(1),

    /**
     * 最新
     */
    NEWEST(2),

    /**
     * 星评值
     */
    START_VALUE(3),

    /**
     * 图片
     */
    IMAGE(4),

    /**
     * 视频
     */
    VIDEO(5),

    /**
     * 追加评论
     */
    ADDITIONAL_COMMENT(6),

    /**
     * 回复
     */
    REPLY_COMMENT(7),

    /**
     * 标签
     */
    LABEL(8),

    /**
     * 无
     */
    NONE(9);

    private final Integer code;


    CommentStatisticTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CommentStatisticTypeEnum of(CommentResourceTypeEnum typeEnum) {
        switch (typeEnum) {
            case IMAGE:
                return IMAGE;
            case VIDEO:
                return VIDEO;
            default:
                return null;
        }
    }

    public static CommentStatisticTypeEnum getByCode(Integer code) {
        for (CommentStatisticTypeEnum item : CommentStatisticTypeEnum.values()) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 根据统计类型获取特定的统计名称
     * @param type 统计类型
     * @return 有图 | 视频 | 追加
     */
    public static String getStatisticName(Integer type) {
        CommentStatisticTypeEnum typeEnum = Optional.ofNullable(getByCode(type)).orElse(NONE);
        switch (typeEnum) {
            case COMMENT:
                return "全部";
            case NEWEST:
                return "最新";
            case IMAGE:
                return "有图";
            case VIDEO:
                return "视频";
            case ADDITIONAL_COMMENT:
                return "追加";
            case REPLY_COMMENT:
                return "有回复";
            default:
                return null;
        }
    }

}
