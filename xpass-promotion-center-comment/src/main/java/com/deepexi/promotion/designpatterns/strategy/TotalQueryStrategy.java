package com.deepexi.promotion.designpatterns.strategy;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.domain.CommentInfoQuery;
import com.deepexi.promotion.domain.CommentInfoQueryDTO;
import com.deepexi.promotion.domain.CommentStatisticTypeQuery;
import com.deepexi.promotion.enums.CommentStatisticTypeEnum;
import com.deepexi.promotion.service.CommentInfoService;
import com.deepexi.promotion.utils.PageUtil;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.StringUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 具体策略 -> 查询所有
 *
 * @author liaoxiaoxin
 * @date 2019/6/19 10:49
 */
@Component
@Slf4j
public class TotalQueryStrategy implements StatisticQueryStrategy {

    private final static int DEFAULT_PAGE = 1;
    private final static int DEFAULT_SIZE = 10;

    @Resource
    private CommentInfoService commentInfoService;

    @Override
    public Integer getType() {
        return CommentStatisticTypeEnum.COMMENT.getCode();
    }

    @Override
    public List<CommentInfoQueryDTO> query(CommentStatisticTypeQuery statisticTypeQuery) {
        // COMMENT:INFO:appId:appBusinessId:modelId:targetId
        String cacheKey = statisticTypeQuery.getAppId() + ":" +
                statisticTypeQuery.getAppBusinessId() + ":" + statisticTypeQuery.getModelId() + ":" +
                statisticTypeQuery.getTargetId();
        // 先查询缓存(缓存只放第一页)
        if (statisticTypeQuery.getPage() == DEFAULT_PAGE && statisticTypeQuery.getSize() == DEFAULT_SIZE) {
            List<CommentInfoQueryDTO> result = listCommentInfoFromRedis(cacheKey);
            // 查不到数据
            if (result == null) {
                // 获取锁,成功则查询数据库,放入缓存
                if (RedisUtils.lock(CommentConstants.COMMENT_INFO_LOCK_KEY + cacheKey)) {
                    try {
                        List<CommentInfoQueryDTO> fromDb = listCommentInfoFromDb(statisticTypeQuery);
                        log.info("加入缓存：{}", fromDb);
                        // 加入缓存
                        RedisUtils.setCache(CommentConstants.COMMENT_INFO_KEY + cacheKey,
                                JSON.toJSONString(PageUtil.changeToPageInfo(fromDb)));
                        return fromDb;
                    } finally {
                        RedisUtils.releaseLock(CommentConstants.COMMENT_INFO_LOCK_KEY + cacheKey);
                    }
                }
                // 获取不到锁，已有线程在请求查库，休眠500m后再次查询缓存
                ThreadUtil.sleep(2000);
                return query(statisticTypeQuery);
            }
            return result;
        }
        // 非查询第一页,不走缓存
        return listCommentInfoFromDb(statisticTypeQuery);
    }

    /**
     * 查询缓存
     *
     * @param key key
     * @return 评价内容
     */
    private List<CommentInfoQueryDTO> listCommentInfoFromRedis(String key) {
        String value = RedisUtils.getCache(CommentConstants.COMMENT_INFO_KEY, key);
        if (StringUtil.isNotBlank(value)) {
            log.info("查询评论内容命中缓存:key={}", key);
            PageInfo<CommentInfoQueryDTO> pageInfo = JSON.parseObject(value,
                    new TypeReference<PageInfo<CommentInfoQueryDTO>>() {
                    });
            return PageUtil.changeToPage(pageInfo);
        }
        return null;
    }

    /**
     * 查询数据库
     *
     * @param statisticTypeQuery 查询参数
     * @return 评价内容列表
     */
    private List<CommentInfoQueryDTO> listCommentInfoFromDb(CommentStatisticTypeQuery statisticTypeQuery) {
        statisticTypeQuery.setRelevanceId(null);
        CommentInfoQuery commentInfoQuery = statisticTypeQuery.clone(CommentInfoQuery.class);
        return commentInfoService.listCommentInfoQueryDTO(commentInfoQuery);
    }
}
