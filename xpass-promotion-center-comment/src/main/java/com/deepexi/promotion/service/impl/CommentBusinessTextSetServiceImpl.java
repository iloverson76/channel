package com.deepexi.promotion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.CommentBusinessTextSetDAO;
import com.deepexi.promotion.domain.CommentBusinessTemplateSetQuery;
import com.deepexi.promotion.domain.CommentSupportSettingDTO;
import com.deepexi.promotion.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deepexi.promotion.domain.CommentBusinessTextSetDO;
import com.deepexi.promotion.domain.CommentBusinessTextSetDTO;
import com.deepexi.promotion.service.CommentBusinessTextSetService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;

/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@Service
@Slf4j
public class CommentBusinessTextSetServiceImpl implements CommentBusinessTextSetService {

    @Autowired
    private CommentBusinessTextSetDAO commentBusinessTextSetDAO;

    @Override
    public Boolean updateCommentBusinessTextSet(Long id, CommentBusinessTextSetDTO dto) {
        dto.setId(id);
        CommentBusinessTextSetDO setDO = dto.clone(CommentBusinessTextSetDO.class);
        setDO.setSupportSettings(JSONObject.toJSONString(dto.getSupportSettings()));
        Boolean flag = commentBusinessTextSetDAO.updateCommentBusinessTextSet(setDO);
        RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, setDO.getId());
        return flag;
    }

    @Override
    public List<CommentBusinessTextSetDTO> findListByConnectIds(CommentBusinessTemplateSetQuery query) {
        if (query.getConnectIds() == null || query.getConnectIds().length <= 0) {
            return new ArrayList<>();
        }
        QueryWrapper<CommentBusinessTextSetDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", query.getType());
        queryWrapper.in("business_model_connect_id", query.getConnectIds());
        List<CommentBusinessTextSetDO> list = commentBusinessTextSetDAO.list(queryWrapper);
        List<CommentBusinessTextSetDTO> dtoList = ObjectCloneUtils.convertList(list, CommentBusinessTextSetDTO.class, CloneDirection.OPPOSITE);
        Map<Long, CommentBusinessTextSetDO> doMap = list.stream().collect(Collectors.toMap(CommentBusinessTextSetDO::getId, x -> x));
        for (CommentBusinessTextSetDTO setDTO : dtoList) {
            setDTO.setSupportSettings(
                    JSONObject.parseObject(doMap.get(setDTO.getId())
                            .getSupportSettings(), CommentSupportSettingDTO.class)
            );
        }
        return dtoList;
    }
}