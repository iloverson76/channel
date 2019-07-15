package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.*;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * comment_label_group
 * @author
 */
public interface CommentLabelGroupDAO extends IService<CommentLabelGroupDO> {

    /**
     * 分页获取列表
     *
     * @param dao
     * @param page
     * @param size
     * @return
     */
    List<CommentLabelGroupDO> listPageCommentLabelGroups(CommentLabelGroupQuery dao);
    /**
     * 获取列表
     *
     * @return
     */
    List<CommentLabelGroupDO> listCommentLabelGroups(CommentLabelGroupQuery dao);

    /**
     * 获取详情
     *
     * @return
     */
    CommentLabelGroupDO getCommentLabelGroup(Long id);

    /**
     * 更新
     *
     * @param id
     * @param dao
     * @return
     */
    Boolean updateCommentLabelGroup( CommentLabelGroupDO dao);

    /**
     * 新增
     *
     * @param dao
     * @return
     */
    Boolean insertCommentLabelGroup(CommentLabelGroupDO dao);


    /**
     * 批量删除
     *
     * @return
     */
    Boolean deleteCommentLabelGroups(Long... ids);

    /**
     * 查询标签和关联列表
     * @param query
     * @return
     */
    List<CommentLabelDetailDO> findLabelDetail(CommentLabelDetailQuery query);

    /**
     * appId下标签组同名查询
     * @param name 名称
     * @param appId id
     * @return
     */
    Integer findSameNameList(String name,Long appId,Long id);



}