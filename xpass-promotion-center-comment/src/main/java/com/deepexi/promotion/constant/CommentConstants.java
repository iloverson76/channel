package com.deepexi.promotion.constant;

/**
 * 评价中心统一常量定义
 *
 * @author zhoujiawen
 */
public interface CommentConstants {

    /**
     * 统计编码 星评(长度：7)
     * 这里冗余上starTemplateId字段是为了与标签的统计编码区分开来
     * appId-appBusinessId-modelId-type-starTemplateId-starTemplateDetailId-targetId
     */
    String STATISTIC_STAR = "%d-%d-%d-%d-%d-%d-%s";

    /**
     * 统计编码 无星评(长度：5)
     * 主要是针对 有图、视频、追加、回复 场景的统计
     * appId-appBusinessId-modelId-type-targetId
     */
    String STATISTIC_NOT_STAR = "%d-%d-%d-%d-%s";

    /**
     * 统计编码 标签(长度：6)
     * appId-appBusinessId-modelId-type-labelTemplateId-targetId
     */
    String STATISTIC_LABEL = "%d-%d-%d-%d-%d-%s";

    /**
     * 评价文本设置 默认串
     */
    String DEFAULT_SUPPORT_SETTING = "{\"support_text\":false,\"support_picture\":false,\"support_video\":false}";

    /**
     * 模板信息的缓存key
     */
    String TEMPLATE_INFO_KEY = "TEMPLATE:INFO:";

    /**
     * 模板信息查询刷新缓存的锁前缀
     */
    String TEMPLATE_INFO_LOCK_KEY = "TEMPLATE:INFO:LOCK:";

    /**
     * 评价内容缓存key
     */
    String COMMENT_INFO_KEY = "COMMENT:INFO:";


    /**
     * 评价内容缓存的锁前缀
     */
    String COMMENT_INFO_LOCK_KEY = "COMMENT:INFO:LOCK:";
}
