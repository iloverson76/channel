package com.deepexi.promotion.domain;

import com.deepexi.promotion.enums.CommentRelevanceTypeEnum;
import com.deepexi.promotion.enums.CommentResourceTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 评价资源接口
 * 使实现此接口的类拥有统一设置资源(文本、图片、视频)的能力
 * @author liaoxiaoxin
 * @date 2019/5/23 11:48
 */
public interface CommentResources {

    /**
     * 获取资源id
     * @return 资源id
     */
    Long getId();

    /**
     * 设置文本
     * @param commentText 文本内容
     */
    void setCommentText(String commentText);

    /**
     * 设置图片
     * @param commentImage 图片URL
     */
    void setCommentImage(String commentImage);

    /**
     * 设置视频
     * @param commentVideo 视频URL
     */
    void setCommentVideo(String commentVideo);

    /**
     * 设置资源(文本、图片、视频)
     * @param resourceMap 资源Map(key:ID-CommentResourceTypeEnum.code)
     */
    default void setCommentResource(Map<String, String> resourceMap) {
        this.setCommentText(
                resourceMap.get(StringUtils.joinWith("-", this.getId(), CommentResourceTypeEnum.TEXT.getCode())));
        this.setCommentImage(
                resourceMap.get(StringUtils.joinWith("-", this.getId(), CommentResourceTypeEnum.IMAGE.getCode())));
        this.setCommentVideo(
                resourceMap.get(StringUtils.joinWith("-", this.getId(), CommentResourceTypeEnum.VIDEO.getCode())));
    }

    /**
     *  设置资源(文本、图片、视频)
     * @param resourceMap 资源Map(key:CommentRelevanceTypeEnum-ID-CommentResourceTypeEnum.code)
     * @param relevanceType 资源类型 CommentRelevanceTypeEnum
     */
    default void setCommentResource(Map<String, String> resourceMap, CommentRelevanceTypeEnum relevanceType) {
        this.setCommentText(
                resourceMap.get(StringUtils.joinWith("-", relevanceType.getCode(), this.getId(), CommentResourceTypeEnum.TEXT.getCode())));
        this.setCommentImage(
                resourceMap.get(StringUtils.joinWith("-", relevanceType.getCode(), this.getId(), CommentResourceTypeEnum.IMAGE.getCode())));
        this.setCommentVideo(
                resourceMap.get(StringUtils.joinWith("-", relevanceType.getCode(), this.getId(), CommentResourceTypeEnum.VIDEO.getCode())));
    }
}
