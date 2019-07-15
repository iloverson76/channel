package com.deepexi.promotion.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.promotion.dao.*;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.service.CommentStarTemplateAndLabelService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.*;


/**
 * @author zhangyanpping
 * @date 2019/5/27
 **/
@Service
@Slf4j
public class CommentStarTemplateAndLabelServiceImpl implements CommentStarTemplateAndLabelService {
    @Autowired
    private CommentStarTemplateDAO commentStarTemplateDAO;
    @Autowired
    private CommentStarTemplateDetailDAO commentStarTemplateDetailDAO;
    @Autowired
    private CommentStarTemplateDetailLabelConnectDAO commentStarTemplateDetailLabelConnectDAO;
    @Autowired
    private CommentLabelTemplateDao commentLabelTemplateDao;

    @Override
    public List<CommentStarTemplateDTO> findList(CommentStarTemplatePageQuery query) {
        if (query != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        //1.查模板 无数据则返回
        List<CommentStarTemplateDO> templateList = commentStarTemplateDAO.findList(query);
        if (CollectionUtils.isEmpty(templateList)) {
            return new ArrayList<>();
        }
        List<CommentStarTemplateDTO> templateDTOList = ObjectCloneUtils.convertList(templateList, CommentStarTemplateDTO.class, CloneDirection.OPPOSITE);

        //2.查星级详细
        Set<Long> starTemplateIds = templateList.stream().map(CommentStarTemplateDO::getId).collect(toSet());
        List<CommentStarTemplateDetailDO> detailList = commentStarTemplateDetailDAO.selectByStarTemplateIds(starTemplateIds);
        //无星级数据则返回
        if (CollectionUtils.isEmpty(detailList)) {
            return templateDTOList;
        }
        List<CommentStarTemplateDetailDTO> detailDTOList = ObjectCloneUtils.convertList(detailList, CommentStarTemplateDetailDTO.class, CloneDirection.OPPOSITE);
        Map<Long,CommentStarTemplateDetailDO> detailDTOMap=detailList.stream().collect(Collectors.toMap(CommentStarTemplateDetailDO::getId,x->x));
        for (CommentStarTemplateDetailDTO dto:detailDTOList){
            dto.setSupportSettings(
                    JSONObject.parseObject(detailDTOMap.get(dto.getId())
                            .getSupportSettings(), CommentStarTemplateDetailSupportSettingsDTO.class));

        }
        //3.查星级标签关联
        Set<Long> detailIds = detailList.stream().map(CommentStarTemplateDetailDO::getId).collect(toSet());
        List<CommentStarTemplateDetailLabelConnectDO> connectList = commentStarTemplateDetailLabelConnectDAO.selectByStarTemplateDetailIds(detailIds);
        if (CollectionUtils.isNotEmpty(connectList)) {
            //星级详细关联标签开始
            Long[] labelTIds = connectList.stream().map(CommentStarTemplateDetailLabelConnectDO::getLabelTemplateId).distinct().toArray(Long[]::new);
            CommentLabelDetailQuery commentLabelDetailQuery = new CommentLabelDetailQuery();
            commentLabelDetailQuery.setLabelIds(labelTIds);
            List<CommentLabelDetailDO> labelTemplates = commentLabelTemplateDao.findLabelDetail(commentLabelDetailQuery);
            if(labelTemplates!=null&&labelTemplates.size()>0){
                Map<Long, CommentLabelDetailDO> labelMap = labelTemplates.stream().collect(Collectors.toMap(CommentLabelDetailDO::getLabelTemplateId, x -> x));
                for (int i = 0; i < connectList.size(); i++) {
                    if(labelMap.get(connectList.get(i).getLabelTemplateId())!=null){
                        connectList.get(i).setLabel(labelMap.get(connectList.get(i).getLabelTemplateId()));
                    }
                }
                Map<Long, List<CommentStarTemplateDetailLabelConnectDO>> connMap = connectList.stream().collect(Collectors.groupingBy(CommentStarTemplateDetailLabelConnectDO::getStarTemplateDetailId));
                for (CommentStarTemplateDetailDTO dto : detailDTOList) {
                    if (connMap.get(dto.getId()) != null) {
                        List<CommentLabelDetailDO> list = connMap.get(dto.getId()).stream()
                                .filter(x->x.getLabel()!=null)
                                .map(CommentStarTemplateDetailLabelConnectDO::getLabel)
                                .collect(Collectors.toList());
                            dto.setLabelList(ObjectCloneUtils.convertList(list,CommentLabelDetailDTO.class,CloneDirection.OPPOSITE));
                    }
                }
            }

        }

        //星级详细关联标签结束
        //星评模板关联星级开始
        Map<Long, List<CommentStarTemplateDetailDTO>> detailMap = detailDTOList.stream().collect(Collectors.groupingBy(CommentStarTemplateDetailDTO::getStarTemplateId));
        for (CommentStarTemplateDTO dto : templateDTOList) {
            dto.setDetails(detailMap.get(dto.getId()));
        }
        //星评模板关联星级开始
        return templateDTOList;
    }

}
