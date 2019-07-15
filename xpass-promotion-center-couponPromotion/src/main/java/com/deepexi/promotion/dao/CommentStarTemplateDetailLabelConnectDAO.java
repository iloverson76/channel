package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentStarTemplateDetailLabelConnectDO;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
public interface CommentStarTemplateDetailLabelConnectDAO extends IService<CommentStarTemplateDetailLabelConnectDO> {
    List<CommentStarTemplateDetailLabelConnectDO> selectByStarTemplateDetailIds(Set<Long> detailIds);

    int removeByStarTemplateDetailIds(Set<Long> detailIds);

    int removeByDO(Set<CommentStarTemplateDetailLabelConnectDO> connects,String updateBy);

    List<CommentStarTemplateDetailLabelConnectDO> selectByLabelTemplateIds(Long... LabelIds);
}
