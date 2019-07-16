package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentStatisticDAO;
import com.deepexi.promotion.designpatterns.strategy.StatisticQueryStrategy;
import com.deepexi.promotion.designpatterns.strategy.StatisticQueryStrategyFactory;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentStatisticTypeEnum;
import com.deepexi.promotion.mapper.CommentStatisticMapper;
import com.deepexi.promotion.service.CommentStatisticService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Service
@Slf4j
public class CommentStatisticServiceImpl
        extends ServiceImpl<CommentStatisticMapper, CommentStatisticDO> implements CommentStatisticService {

    @Resource
    private CommentStatisticDAO commentStatisticDAO;

    @Resource
    private StatisticQueryStrategyFactory queryStrategyFactory;

    @Override
    public boolean batchInsertOrUpdate(List<CommentStatisticDO> list) {
        return commentStatisticDAO.batchInsertOrUpdate(list);
    }

    @Override
    public List<CommentStatisticDTO> listStatistics(CommentStatisticQuery statisticQuery) {
        List<CommentStatisticDO> statisticDOList = commentStatisticDAO.listStatistics(statisticQuery);
        if (CollectionUtil.isEmpty(statisticDOList)) {
            return CollectionUtil.createArrayList();
        }

        List<CommentStatisticDTO> statisticDTOList = ObjectCloneUtils.convertList(statisticDOList, CommentStatisticDTO.class);
        // 设置有图,视频，追加等
        statisticDTOList.forEach((statisticDTO) -> {
            String name = CommentStatisticTypeEnum.getStatisticName(statisticDTO.getType());
            if (name != null) {
                statisticDTO.setName(name);
            }
        });
        // 排序(type - id)
        return statisticDTOList.stream()
                .sorted(Comparator.comparingInt(CommentStatisticDTO::getType)).collect(Collectors.toList());
    }

    @Override
    public List<CommentInfoQueryDTO> listCommentByStatisticType(CommentStatisticTypeQuery statisticTypeQuery) {
        StatisticQueryStrategy instance = queryStrategyFactory.getInstance(statisticTypeQuery.getType());
        return instance.query(statisticTypeQuery);
    }
}