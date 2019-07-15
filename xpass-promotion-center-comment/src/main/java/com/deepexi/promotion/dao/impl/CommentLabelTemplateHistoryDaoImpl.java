package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentLabelTemplateHistoryDao;
import com.deepexi.promotion.domain.CommentLabelTemplateHistoryDO;
import com.deepexi.promotion.mapper.CommentLabelTemplateHistoryMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
@Repository
public class CommentLabelTemplateHistoryDaoImpl extends ServiceImpl<CommentLabelTemplateHistoryMapper, CommentLabelTemplateHistoryDO> implements CommentLabelTemplateHistoryDao {

    @Autowired
    private CommentLabelTemplateHistoryMapper mapper;

    @Override
    public int deleteByLabelTemplateIds(Set<Long> labelTemplateIds) {
//        mapper.deleteByLabelTemplateIds(labelTemplateIds );
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("label_template_id",labelTemplateIds);
        return mapper.delete(queryWrapper);
    }

    @Override
    public List<CommentLabelTemplateHistoryDO> selectByLabelTemplateIds(Set<Long> tIds) {
        if (CollectionUtils.isEmpty(tIds))
            return Collections.EMPTY_LIST;

        List<CommentLabelTemplateHistoryDO> list = mapper.selectByLabelTemplateId(tIds);
        return list;
    }
}
