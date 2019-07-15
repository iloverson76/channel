package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.*;
import java.util.List;

/**
 * comment_label_group
 * @author
 */
public interface CommentLabelGroupService  {


    /**
     * 更新
     *
     * @param id
     * @param dto
     * @return
     */
    Boolean updateCommentLabelGroup(Long id, CommentLabelGroupInputDTO dto);

    /**
     * 新增标签组基本信息
     *
     * @param dto
     * @return
     */
    Boolean insertCommentLabelGroup(CommentLabelGroupInputDTO dto);

    /**
     * 新增标签组关联关系
     * @param groupId 标签组id
     * @param appId 应用id
     * @param lableIds 标签集合id
     * @return
     */
    Boolean insertCommentLabelGroupConnect(Long groupId,Long appId,List<Long> lableIds);


    /**
     * 批量删除
     *
     * @return
     */
    Boolean deleteCommentLabelGroups(Long... ids);

    /**
     * 查询标签和关联列表 分页
     * @param query
     * @return
     */
    List<CommentLabelGroupDTO> findPageLabelGroupAndDetail(CommentLabelGroupQuery query);

}