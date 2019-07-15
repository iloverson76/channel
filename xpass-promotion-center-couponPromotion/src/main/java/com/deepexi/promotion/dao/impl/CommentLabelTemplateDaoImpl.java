package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentLabelTemplateDao;
import com.deepexi.promotion.dao.CommentStarTemplateDAO;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.mapper.CommentLabelGroupConnectMapper;
import com.deepexi.promotion.mapper.CommentLabelTemplateMapper;
import com.deepexi.promotion.mapper.CommentStarTemplateDetailLabelConnectMapper;
import com.deepexi.promotion.mapper.CommentStarTemplateMapper;
import com.deepexi.util.CollectionUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
@Repository
public class CommentLabelTemplateDaoImpl extends ServiceImpl<CommentLabelTemplateMapper, CommentLabelTemplateDO> implements CommentLabelTemplateDao {

    @Autowired
    private CommentLabelTemplateMapper commentLabelTemplateMapper;
    @Autowired
    private CommentLabelGroupConnectMapper commentLabelGroupConnectMapper;
    @Autowired
    private CommentStarTemplateDetailLabelConnectMapper commentStarTemplateDetailLabelConnectMapper;
    @Autowired
    private CommentStarTemplateMapper commentStarTemplateMapper;

    @Override
    public List<CommentLabelTemplateDO> findPage(CommentLabelTemplateQuery query) {
        CommentLabelTemplateDO labelTemplateDO = query.clone(CommentLabelTemplateDO.class);
        QueryWrapper<CommentLabelTemplateDO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(labelTemplateDO.getName())) {
            queryWrapper.like("name", labelTemplateDO.getName());
        }
        if (StringUtils.isNotEmpty(labelTemplateDO.getAppId())) {
            queryWrapper.eq("app_id", labelTemplateDO.getAppId());
        }
        if (labelTemplateDO.getType() != null) {
            queryWrapper.eq("type", labelTemplateDO.getType());
        }
        queryWrapper.orderByDesc("id");
        return commentLabelTemplateMapper.selectList(queryWrapper);
    }

    @Override
    public CommentLabelTemplateDO selectById(Long id) {

        QueryWrapper<CommentLabelTemplateDO> queryWrapper = new QueryWrapper<>();
        CommentLabelTemplateDO templateDO = new CommentLabelTemplateDO();
        templateDO.setId(id);
        queryWrapper.setEntity(templateDO);
        return commentLabelTemplateMapper.selectOne(queryWrapper);
    }

    @Override
    public CommentLabelTemplateDO selectByAppIdAndName(String appId, String name) {
        QueryWrapper<CommentLabelTemplateDO> queryWrapper = new QueryWrapper<>();
        CommentLabelTemplateDO templateDO = new CommentLabelTemplateDO();
        templateDO.setAppId(appId);
        templateDO.setName(name);
        queryWrapper.setEntity(templateDO);
        return commentLabelTemplateMapper.selectOne(queryWrapper);
    }

    @Override
    public List<CommentLabelDetailDO> findLabelDetail(CommentLabelDetailQuery query) {
        return commentLabelTemplateMapper.findLabelDetail(query);
    }

    @Override
    public List<Long> selectBusinessIds(Long... ids) {

        //先查询 当前标签关联的标签组
        QueryWrapper<CommentLabelGroupConnectDO> var0 = new QueryWrapper<>();
        var0.select("label_group_id").in("label_template_id", Arrays.asList(ids)).eq("dr", 0);
        List<CommentLabelGroupConnectDO> labelGroupList = commentLabelGroupConnectMapper.selectList(var0);
        List<Long> labelGroupIds = labelGroupList.stream().map(CommentLabelGroupConnectDO::getLabelGroupId).collect(Collectors.toList());

        //查询当前关联的星评
        List<Long> starTemplateIds = commentStarTemplateDetailLabelConnectMapper.selectStarTemplateByLabelIds(Arrays.asList(ids));

        List<Long> businessIds = Lists.newArrayList();
        if (CollectionUtil.isNotEmpty(labelGroupIds)) {
            businessIds.addAll(commentLabelGroupConnectMapper.selectBusinessIds(labelGroupIds));
        }
        if (CollectionUtil.isNotEmpty(starTemplateIds)) {
            businessIds.addAll(commentStarTemplateMapper.selectBusinessIds(starTemplateIds));
        }
        return businessIds;
    }
}
