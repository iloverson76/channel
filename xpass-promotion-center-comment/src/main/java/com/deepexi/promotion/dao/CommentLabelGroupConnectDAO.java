package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentLabelGroupConnectDO;
import com.deepexi.promotion.domain.CommentLabelGroupConnectQuery;

import java.util.List;

/**
 * comment_label_group_connect
 *
 * @author
 */
public interface CommentLabelGroupConnectDAO extends IService<CommentLabelGroupConnectDO> {

    /**
     * 分页获取列表
     *
     * @param dto
     * @param page
     * @param size
     * @return
     */
    List<CommentLabelGroupConnectDO> listPageCommentLabelGroupConnects(CommentLabelGroupConnectQuery dto);

    /**
     * 获取列表
     *
     * @return
     */
    List<CommentLabelGroupConnectDO> listCommentLabelGroupConnects(CommentLabelGroupConnectQuery dto);

    /**
     * 获取详情
     *
     * @return
     */
    CommentLabelGroupConnectDO getCommentLabelGroupConnect(Long id);

    /**
     * 更新
     *
     * @param id
     * @param dto
     * @return
     */
    Boolean updateCommentLabelGroupConnect(CommentLabelGroupConnectDO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Boolean insertCommentLabelGroupConnect(CommentLabelGroupConnectDO dto);


    /**
     * 批量删除
     *
     * @return
     */
    Boolean deleteCommentLabelGroupConnects(Long... ids);

    /**
     * 批量插入标签组关联标签
     *
     * @param dos
     * @return
     */
    int insertCommentLabelGroupConnectBatch(List<CommentLabelGroupConnectDO> dos);

    /**
     * 根据标签组id查找关联的业务id
     * @param id 标签组id
     * @return
     */
    List<Long> selectBusinessIds(Long... id);

    /**
     * 批量查找评价关联信息
     * @param labelTemplateIds
     * @return
     */
    List<CommentLabelGroupConnectDO> listCommentLabelGroupConnectsByLabelTemplateIds(Long...labelTemplateIds);
}