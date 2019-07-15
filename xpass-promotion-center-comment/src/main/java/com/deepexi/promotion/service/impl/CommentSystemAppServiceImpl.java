package com.deepexi.promotion.service;

import com.deepexi.promotion.dao.CommentSystemAppDAO;
import com.deepexi.promotion.domain.CommentSystemAppDO;
import com.deepexi.promotion.domain.CommentSystemAppDTO;
import com.deepexi.promotion.domain.CommentSystemAppQuery;
import com.deepexi.promotion.domain.CommentSystemAppSecretDTO;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.promotion.service.CommentSystemAppService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.IdGenerator;
import com.deepexi.util.MD5Util;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author
 */
@Service
public class CommentSystemAppServiceImpl implements CommentSystemAppService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 生成secret密钥
     */
    private final static String SECRET_KEY = "promotion";

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private CommentSystemAppDAO commentSystemAppDAO;

    @Override
    public List<CommentSystemAppDTO> listAllCommentSystemApp(CommentSystemAppQuery query) {
        Integer page = query.getPage();
        Integer size = query.getSize();

        if(page != null && page != -1) {
            PageHelper.startPage(page, size);
        }

        List<CommentSystemAppDO> list =  commentSystemAppDAO.listAllCommentSystemApp(query);
        return ObjectCloneUtils.convertList(list, CommentSystemAppDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public CommentSystemAppDTO getCommentSystemApp (Long  id) {
        CommentSystemAppDO result = commentSystemAppDAO.getById(id);
        if(result != null) {
            return result.clone(CommentSystemAppDTO.class);
        }
        return null;
    }


    @Override
    public CommentSystemAppDTO getOneIfNotPresentThrowException(Long appId) throws ApplicationException {
        return Optional.ofNullable(this.getCommentSystemApp(appId))
                .orElseThrow(() -> new ApplicationException(ResultEnum.APP_NOT_EXIST));
    }

    @Override
    public Boolean updateCommentSystemApp(Long  id, CommentSystemAppDTO dto) {
        // 判断名称是否存在,名称存在，则判断名称是否重复
        if(dto.getName() != null) {
            CommentSystemAppQuery query = new CommentSystemAppQuery();
            query.setName(dto.getName());
            query.setTenantId(appRuntimeEnv.getTenantId());
            List<CommentSystemAppDTO> allCommentSystemApp = this.listAllCommentSystemApp(query);
            if (!CollectionUtil.isEmpty(allCommentSystemApp) && !allCommentSystemApp.get(0).getId().equals(id)) {
                logger.error("应用名已存在，name:{}", dto.getName());
                throw new ApplicationException(ResultEnum.APPNAME_EXIST);
            }
        }
        dto.setId(id);
        return commentSystemAppDAO.updateById(dto.clone(CommentSystemAppDO.class));
    }

    @Override
    public Boolean saveCommentSystemApp(CommentSystemAppDTO dto) {
        // 判断名称是否存在
        CommentSystemAppQuery query = new CommentSystemAppQuery();
        query.setName(dto.getName());
        query.setTenantId(appRuntimeEnv.getTenantId());
        List<CommentSystemAppDTO> allCommentSystemApp = this.listAllCommentSystemApp(query);
        if(!CollectionUtil.isEmpty(allCommentSystemApp)){
            logger.error("应用名已存在，name:{}",dto.getName());
            throw new ApplicationException(ResultEnum.APPNAME_EXIST);
        }

        // 生成agentId 和 secret
        CommentSystemAppSecretDTO secret = this.getAppSecret(SECRET_KEY);
        dto.setAgentId(secret.getAgentId());
        dto.setSecret(secret.getSecret());
        logger.info("创建应用，systemAppDTO：{}",dto);
        return commentSystemAppDAO.save(dto.clone(CommentSystemAppDO.class));
    }


    @Override
    public Boolean deleteCommentSystemApps (Long ...ids) {
        int result = commentSystemAppDAO.deleteByIds(Arrays.asList(ids));
        return result > 0;
    }

    @Override
    public CommentSystemAppSecretDTO getAppSecret(String secretKey) {
        // 生成agentId 和 secret
        String agentId = IdGenerator.getUuId();
        String secret = MD5Util.generate(secretKey+agentId);
        return new CommentSystemAppSecretDTO(agentId,secret);
    }

    @Override
    public CommentSystemAppSecretDTO regainSecret(Long id) {
        CommentSystemAppSecretDTO secret = this.getAppSecret(SECRET_KEY);
        CommentSystemAppDO systemApp = new CommentSystemAppDO();
        systemApp.setAgentId(secret.getAgentId());
        systemApp.setSecret(secret.getSecret());
        systemApp.setId(id);

        logger.info("更新应用agentId 和 secret，dto:{}",systemApp);
        if(!commentSystemAppDAO.updateById(systemApp)){
            throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
        }
        return secret;
    }

    @Override
    public Boolean enableAppCheck(Long id, Boolean commentCheck) {
        CommentSystemAppDO systemApp = new CommentSystemAppDO();
        systemApp.setId(id);
        systemApp.setCommentCheck(commentCheck);
        return commentSystemAppDAO.updateById(systemApp);
    }

    @Override
    public Boolean enbaleApp(Long id, Boolean enable) {
        CommentSystemAppDO systemApp = new CommentSystemAppDO();
        systemApp.setId(id);
        systemApp.setEnable(enable);
        return commentSystemAppDAO.updateById(systemApp);
    }
}