package com.deepexi.promotion.service;

import java.util.List;

import com.deepexi.promotion.domain.CommentAppBusinessDTO;
import com.deepexi.promotion.domain.CommentAppBusinessQuery;
import com.deepexi.util.extension.ApplicationException;

/**
 * comment_app_business
 */
public interface CommentAppBusinessService {

    /**
     * 获取列表
     *
     * @return
     */
    List<CommentAppBusinessDTO> listAllCommentAppBusiness(CommentAppBusinessQuery query);

    /**
     * 获取详情
     *
     * @return
     */
    CommentAppBusinessDTO getCommentAppBusiness(Long id);

    /**
     * 更新
     *
     * @param id 主键id
     * @param dto 更新实体
     * @return
     */
    Boolean updateCommentAppBusiness(Long id, CommentAppBusinessDTO dto);

    /**
     * 新增
     *
     * @param dto 新增实体
     * @return
     */
    Boolean saveCommentAppBusiness(CommentAppBusinessDTO dto);


    /**
     * 批量删除
     *
     * @param ids 删除id集合
     * @return
     */
    Boolean deleteCommentAppBusinesss(Long... ids);


    /**
     * 获取列表
     * @param ids 查询的ID集合
     * @return List<CommentAppBusinessDTO>
     */
    List<CommentAppBusinessDTO> listAllCommentAppBusiness(List<Long> ids);

    /**
     * 获取CommentAppBusinessDTO
     *
     * @param appBusinessDTO 业务实体
     * @return 业务实体
     * @throws ApplicationException 获取不到时，抛出BUSINESS_NOT_EXIST异常 {@link com.deepexi.promotion.enums.ResultEnum}
     */
    CommentAppBusinessDTO getByCommentAppBusinessDTO(CommentAppBusinessDTO appBusinessDTO) throws ApplicationException;

}