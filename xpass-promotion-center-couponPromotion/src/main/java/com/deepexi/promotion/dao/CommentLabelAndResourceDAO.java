package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.enums.CommentRelevanceTypeEnum;
import com.deepexi.util.CollectionUtil;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * CommentLabelDAO 和 CommentResourceDAO 共同接口
 *
 * @param <T> DO
 * @author liaoxiaoxin
 * @date 2019/6/3 10:54
 */
public interface CommentLabelAndResourceDAO<T> extends IService<T> {

    String RELEVANCE_ID = "relevance_id";
    String RELEVANCE_TYPE = "relevance_type";

    /**
     * 通过RelevanceId 查询列表
     *
     * @param relevanceIdList RelevanceId 集合
     * @param type            关联类型
     * @return List<T>
     */
    default List<T> listByRelevanceIdList(List<Long> relevanceIdList, CommentRelevanceTypeEnum type) {
        if (CollectionUtil.isEmpty(relevanceIdList)) {
            return ImmutableList.of();
        }
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(RELEVANCE_TYPE, type.getCode()).in(RELEVANCE_ID, relevanceIdList);
        return this.list(wrapper);
    }

    /**
     * 通过RelevanceId 查询列表
     *
     * @param detailIdList 评论明细ID 集合
     * @param starIdList   星评ID 集合
     * @return List<T>
     */
    default List<T> listByRelevanceIdList(List<Long> detailIdList, List<Long> starIdList) {

        // 都为空,直接返回
        if (CollectionUtil.isEmpty(detailIdList) && CollectionUtil.isEmpty(starIdList)) {
            return ImmutableList.of();
        }
        // SQL: (relevance_id in (?..) and relevance_type = 1) or (relevance_id in (?..) and relevance_type = 2)
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (CollectionUtil.isNotEmpty(detailIdList)) {
            wrapper.in(RELEVANCE_ID, detailIdList)
                    .eq(RELEVANCE_TYPE, CommentRelevanceTypeEnum.COMMENT_DETAIL.getCode());
        }
        if (CollectionUtil.isNotEmpty(detailIdList) && CollectionUtil.isNotEmpty(starIdList)) {
            wrapper.or();
        }
        if (CollectionUtil.isNotEmpty(starIdList)) {
            wrapper.in(RELEVANCE_ID, starIdList)
                    .eq(RELEVANCE_TYPE, CommentRelevanceTypeEnum.COMMENT_STAR.getCode());
        }
        return this.list(wrapper);
    }
}
