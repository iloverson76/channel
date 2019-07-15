package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.CommentLabelGroupConnectDTO;
import com.deepexi.promotion.domain.CommentLabelGroupConnectQuery;
import java.util.List;

/**
 * comment_label_group_connect
 * @author
 */
public interface CommentLabelGroupConnectService {

    /**
     * 分页获取列表
     *
     * @param dto 查询参数
     * @return 列表
     */
    List<CommentLabelGroupConnectDTO> listPageCommentLabelGroupConnects(CommentLabelGroupConnectQuery dto);
    /**
     * 获取列表
     *
     * @return
     */
    List<CommentLabelGroupConnectDTO> listCommentLabelGroupConnects(CommentLabelGroupConnectQuery dto);
    /**
     * 获取详情
     *
     * @return
     */
    CommentLabelGroupConnectDTO getCommentLabelGroupConnect(Long id);

    /**
     * 更新
     *
     * @param id
     * @param dto
     * @return
     */
    Boolean updateCommentLabelGroupConnect(Long id, CommentLabelGroupConnectDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Boolean insertCommentLabelGroupConnect(CommentLabelGroupConnectDTO dto);


    /**
     * 批量删除
     *
     * @return
     */
    Boolean deleteCommentLabelGroupConnects(Long... ids);
}