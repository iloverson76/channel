package com.deepexi.promotion.designpatterns.strategy;

import com.deepexi.promotion.domain.CommentInfoQuery;
import com.deepexi.promotion.domain.CommentInfoQueryDTO;
import com.deepexi.promotion.domain.CommentStatisticTypeQuery;
import com.deepexi.promotion.enums.CommentStatisticTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.CommentInfoService;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 具体策略 -> 根据标签查询
 *
 * @author liaoxiaoxin
 * @date 2019/6/19 10:49
 */
@Component
@Slf4j
public class LabelQueryStrategy implements StatisticQueryStrategy {

    @Resource
    private CommentInfoService commentInfoService;

    @Override
    public Integer getType() {
        return CommentStatisticTypeEnum.LABEL.getCode();
    }

    @Override
    public List<CommentInfoQueryDTO> query(CommentStatisticTypeQuery statisticTypeQuery) {
        CommentInfoQuery commentInfoQuery = statisticTypeQuery.clone(CommentInfoQuery.class);
        if(statisticTypeQuery.getRelevanceId() == null){
            log.error("查询包含特定标签的评价内容时,标签ID不能为空");
            throw  new ApplicationException(ResultEnum.PARAM_ERR);
        }
        commentInfoQuery.setLabelTemplateId(statisticTypeQuery.getRelevanceId());
        return commentInfoService.listCommentInfoQueryDTO(commentInfoQuery);
    }
}
