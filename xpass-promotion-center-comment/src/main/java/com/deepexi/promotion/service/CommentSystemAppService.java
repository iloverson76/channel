package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.CommentSystemAppDTO;
import com.deepexi.promotion.domain.CommentSystemAppQuery;
import com.deepexi.promotion.domain.CommentSystemAppSecretDTO;
import com.deepexi.util.extension.ApplicationException;

import java.util.List;

/**
 * comment_system_app
 */
public interface CommentSystemAppService {

    /**
     * 获取列表
     *
     * @return
     */
    List<CommentSystemAppDTO> listAllCommentSystemApp(CommentSystemAppQuery dto);

    /**
     * 获取详情
     *
     * @return
     */
    CommentSystemAppDTO getCommentSystemApp(Long id);

    /**
     * 根据appId获取 CommentSystemAppDTO
     *
     * @param appId 应用ID
     * @return CommentSystemAppDTO
     * @throws ApplicationException 应用不存在异常
     */
    CommentSystemAppDTO getOneIfNotPresentThrowException(Long appId) throws ApplicationException;

    /**
     * 更新
     *
     * @param id
     * @param dto
     * @return
     */
    Boolean updateCommentSystemApp(Long id, CommentSystemAppDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Boolean saveCommentSystemApp(CommentSystemAppDTO dto);


    /**
     * 批量删除
     *
     * @return
     */
    Boolean deleteCommentSystemApps(Long... ids);

    /**
     * 获取应用的agentId和secret
     * @param secretKey 加密密钥
     * @return
     */
    CommentSystemAppSecretDTO getAppSecret(String secretKey);

    /**
     * 重新获取agentId和secret
     * @param id 应用id
     * @return
     */
    CommentSystemAppSecretDTO regainSecret(Long id);

    /**
     * 禁用/启用应用审核
     * @param id 应用id
     * @param commentCheck 禁用/启用
     * @return
     */
    Boolean enableAppCheck(Long id, Boolean commentCheck);

    /**
     * 禁用/启用审核
     * @param id 应用id
     * @param enable 禁用/启用
     * @return
     */
    Boolean enbaleApp(Long id, Boolean enable);


}