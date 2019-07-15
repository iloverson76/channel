package com.deepexi.promotion.designpatterns.template;

import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentResourceTypeEnum;
import com.deepexi.promotion.enums.CommentStatisticTypeEnum;
import com.deepexi.util.CollectionUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

/**
 * 模板模式：评论统计
 *
 * @author liaoxiaoxin
 * @date 2019/6/12 15:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractStatisticTemplate {

    /**
     * 评论类型
     */
    private Integer type;

    /**
     * 根据传入参数进行统计
     *
     * @param statisticParamDTOList List<CommentStatisticParamDTO>
     * @return 评论统计结果
     */
    public final List<CommentStatisticDTO> count(List<CommentStatisticParamDTO> statisticParamDTOList) {

        if (CollectionUtil.isEmpty(statisticParamDTOList)) {
            return new ArrayList<>();
        }
        Set<CommentStatisticDTO> result = new HashSet<>();
        for (CommentStatisticParamDTO statisticDTO : statisticParamDTOList) {
            // 根据评论类型统计(有可能多个评价目标)
            result.add(buildStatisticWithCommentType(statisticDTO));
            if (isCountStar() && CollectionUtil.isNotEmpty(statisticDTO.getCommentStarList())) {
                countStar(statisticDTO, result);
            }
            if (isCountResource()) {
                countResource(statisticDTO, result);
            }
            if (isCountLabel()) {
                countLabel(statisticDTO, result);
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 钩子方法:是否统计标签
     *
     * @return true:是 false:否
     */
    abstract boolean isCountLabel();

    /**
     * 钩子方法:是否统计图片，视频
     *
     * @return true:是 false:否
     */
    abstract boolean isCountResource();

    /**
     * 钩子方法:是否统计星评
     *
     * @return true:是 false:否
     */
    abstract boolean isCountStar();

    /**
     * 根据评论类型生成一条评论统计，留给子类自行实现
     *
     * @param statisticDTO CommentStatisticParamDTO
     * @return 返回一条评论统计
     */
    abstract CommentStatisticDTO buildStatisticWithCommentType(CommentStatisticParamDTO statisticDTO);

    /**
     * 统计星评
     *
     * @param statisticDTO CommentStatisticParamDTO
     * @param result       统计结果集
     */
    private void countStar(CommentStatisticParamDTO statisticDTO, Set<CommentStatisticDTO> result) {

        for (CommentStarInputDTO commentStarInputDTO : statisticDTO.getCommentStarList()) {
            CommentStatisticDTO starStatistic = statisticDTO.clone(CommentStatisticDTO.class);
            starStatistic.setType(CommentStatisticTypeEnum.START_VALUE.getCode());
            starStatistic.setRelevanceId(commentStarInputDTO.getStarTemplateDetailId());
            starStatistic.setStarTemplateId(commentStarInputDTO.getStarTemplateId());
            starStatistic.setName(commentStarInputDTO.getStarAlias());
            buildStatisticCode(starStatistic);
            result.add(starStatistic);
        }

    }

    /**
     * 统计图片，视频
     *
     * @param statisticDTO CommentStatisticParamDTO
     * @param result       统计结果集
     */
    private void countResource(CommentStatisticParamDTO statisticDTO, Set<CommentStatisticDTO> result) {
        List<CommentResourceInputDTO> allResource = Optional.ofNullable(statisticDTO.getCommentResourceList())
                .orElse(CollectionUtil.createArrayList());
        if (CollectionUtil.isNotEmpty(statisticDTO.getCommentStarList())) {
            statisticDTO.getCommentStarList().forEach(
                    commentStarInputDTO -> allResource.addAll(Optional.ofNullable(commentStarInputDTO.getCommentResourceList())
                            .orElse(CollectionUtil.createArrayList())));
        }
        if (CollectionUtil.isNotEmpty(allResource)) {
            allResource.forEach(commentResourceInputDTO -> {
                CommentStatisticDTO resourceStatistic = statisticDTO.clone(CommentStatisticDTO.class);
                CommentStatisticTypeEnum statisticTypeEnum =
                        CommentStatisticTypeEnum.of(CommentResourceTypeEnum.of(commentResourceInputDTO.getResourceType()));
                // 文本无需统计
                if (statisticTypeEnum != null) {
                    resourceStatistic.setType(statisticTypeEnum.getCode());
                    buildStatisticCode(resourceStatistic);
                    result.add(resourceStatistic);
                }
            });
        }
    }

    /**
     * 统计标签
     *
     * @param statisticDTO CommentStatisticParamDTO
     * @param result       统计结果集
     */
    private void countLabel(CommentStatisticParamDTO statisticDTO, Set<CommentStatisticDTO> result) {
        List<CommentLabelInputDTO> allLabel = Optional.ofNullable(statisticDTO.getCommentLabelList())
                .orElse(CollectionUtil.createArrayList());
        if (CollectionUtil.isNotEmpty(statisticDTO.getCommentStarList())) {
            statisticDTO.getCommentStarList().forEach((commentStarInputDTO ->
                    allLabel.addAll(Optional.ofNullable(commentStarInputDTO.getCommentLabelList())
                            .orElse(CollectionUtil.createArrayList()))));
        }
        if (CollectionUtil.isNotEmpty(allLabel)) {
            allLabel.forEach(commentLabelInputDTO -> {
                CommentStatisticDTO labelStatistic = statisticDTO.clone(CommentStatisticDTO.class);
                labelStatistic.setType(CommentStatisticTypeEnum.LABEL.getCode());
                labelStatistic.setRelevanceId(commentLabelInputDTO.getLabelTemplateId());
                labelStatistic.setName(commentLabelInputDTO.getLabelName());
                buildStatisticCode(labelStatistic);
                result.add(labelStatistic);
            });
        }
    }

    /**
     * 设置统计编码
     *
     * @param statisticDTO 需要设计统计编码的实体
     */
    void buildStatisticCode(CommentStatisticDTO statisticDTO) {

        if (CommentStatisticTypeEnum.START_VALUE.getCode().equals(statisticDTO.getType())) {
            // appId-appBusinessId-modelId-type-starTemplateId-starTemplateDetailId-targetId
            String code = String.format(CommentConstants.STATISTIC_STAR, statisticDTO.getAppId(),
                    statisticDTO.getAppBusinessId(), statisticDTO.getModelId(), statisticDTO.getType(),
                    statisticDTO.getStarTemplateId(), statisticDTO.getRelevanceId(), statisticDTO.getTargetId());
            statisticDTO.setStatisticCode(code);
        } else if (CommentStatisticTypeEnum.LABEL.getCode().equals(statisticDTO.getType())) {
            //appId-appBusinessId-modelId-type-labelTemplateId-targetId
            String code = String.format(CommentConstants.STATISTIC_LABEL, statisticDTO.getAppId(), statisticDTO.getAppBusinessId(),
                    statisticDTO.getModelId(), statisticDTO.getType(), statisticDTO.getRelevanceId(), statisticDTO.getTargetId());
            statisticDTO.setStatisticCode(code);
        } else {
            //appId-appBusinessId-modelId-type-targetId
            String code = String.format(CommentConstants.STATISTIC_NOT_STAR, statisticDTO.getAppId(),
                    statisticDTO.getAppBusinessId(), statisticDTO.getModelId(), statisticDTO.getType(), statisticDTO.getTargetId());
            statisticDTO.setStatisticCode(code);
        }
    }
}
