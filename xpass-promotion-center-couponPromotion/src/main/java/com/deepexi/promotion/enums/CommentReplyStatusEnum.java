package com.deepexi.promotion.enums;

/**
 * @author lxx
 */
public enum CommentReplyStatusEnum {


    /**
     * 没有回复记录
     */
    NO_REPLY(0),

    /**
     * 有回复
     */
    REPLIED(1);


    private final Integer status;

    CommentReplyStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public static CommentReplyStatusEnum getByStatus(Integer code){
        for (CommentReplyStatusEnum statusEnum : values()){
            if (statusEnum.getStatus().equals(code)){
                return statusEnum;
            }
        }
        return null;
    }

}
