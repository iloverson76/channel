package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.promotion.dao.CommentLabelTemplateDao;
import com.deepexi.promotion.dao.CommentStarTemplateDetailDAO;
import com.deepexi.promotion.dao.CommentStarTemplateDetailLabelConnectDAO;
import com.deepexi.promotion.domain.CommentStarTemplateDetailDO;
import com.deepexi.promotion.domain.CommentStarTemplateDetailDTO;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.promotion.service.CommentStarTemplateDetailService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/24
 **/
@Service
public class CommentStarTemplateDetailServiceImpl implements CommentStarTemplateDetailService {
    @Autowired
    private CommentStarTemplateDetailDAO commentStarTemplateDetailDAO;
    @Autowired
    private CommentStarTemplateDetailLabelConnectDAO commentStarTemplateDetailLabelConnectDAO;
    @Autowired
    private CommentLabelTemplateDao commentLabelTemplateDao;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public Boolean create(CommentStarTemplateDetailDTO dto) {
        CommentStarTemplateDetailDO record = dto.clone(CommentStarTemplateDetailDO.class);
        return commentStarTemplateDetailDAO.save(record);
    }

    @Override
    public List<CommentStarTemplateDetailDTO> queryByTemplateIds(List<Long> starTemplateIds) {
        if (CollectionUtil.isEmpty(starTemplateIds)) {
            return new ArrayList<>();
        }
        List<CommentStarTemplateDetailDO> commentStarTemplateDetailDOS =
                commentStarTemplateDetailDAO.selectByStarTemplateIds(new HashSet<>(starTemplateIds));
        return ObjectCloneUtils.convertList(commentStarTemplateDetailDOS, CommentStarTemplateDetailDTO.class);
    }

    @Override
    public List<CommentStarTemplateDetailDTO> queryByIds(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return CollectionUtil.createArrayList();
        }
        LambdaQueryWrapper<CommentStarTemplateDetailDO> wrapper = new QueryWrapper<CommentStarTemplateDetailDO>().lambda();
        wrapper.in(CommentStarTemplateDetailDO::getId, ids)
                .eq(CommentStarTemplateDetailDO::getTenantId, appRuntimeEnv.getTenantId());
        return ObjectCloneUtils.convertList(commentStarTemplateDetailDAO.list(wrapper), CommentStarTemplateDetailDTO.class);
    }
}
