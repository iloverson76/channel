package com.deepexi.promotion.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.CommentAppBusinessDAO;
import com.deepexi.promotion.domain.CommentAppBusinessDO;
import com.deepexi.promotion.domain.CommentAppBusinessDTO;
import com.deepexi.promotion.domain.CommentAppBusinessQuery;
import com.deepexi.promotion.domain.CommentAppBusinessSupportSetDTO;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.CommentAppBusinessService;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
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
public class CommentAppBusinessServiceImpl implements CommentAppBusinessService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * code编码初始值
     */
    private final static int MIN_CODE = 1000;

    @Autowired
    private CommentAppBusinessDAO commentAppBusinessDAO;

    @Override
    public List<CommentAppBusinessDTO> listAllCommentAppBusiness(CommentAppBusinessQuery query) {
        Integer page = query.getPage();
        Integer size = query.getSize();

        if (page != null && page != -1) {
            PageHelper.startPage(page, size);
        }
        List<CommentAppBusinessDO> list = commentAppBusinessDAO.listAll(query);
        return ObjectCloneUtils.convertList(list, CommentAppBusinessDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public CommentAppBusinessDTO getCommentAppBusiness(Long id) {
        CommentAppBusinessDO result = commentAppBusinessDAO.getById(id);
        if (result != null) {
            return result.clone(CommentAppBusinessDTO.class);
        }
        return null;
    }

    @Override
    public Boolean updateCommentAppBusiness(Long id, CommentAppBusinessDTO dto) {
        if(StringUtils.isNotBlank(dto.getIdentificationCode())) {
            // 标识码应用间不能相同
            CommentAppBusinessQuery query = new CommentAppBusinessQuery();
            query.setIdentificationCode(dto.getIdentificationCode());
            query.setAppId(dto.getAppId());
            List<CommentAppBusinessDTO> commentAppBusinessDTOS = this.listAllCommentAppBusiness(query);
            if (!CollectionUtil.isEmpty(commentAppBusinessDTOS) && !commentAppBusinessDTOS.get(0).getId().equals(id)) {
                logger.error("标识码已存在：appId:{},id:{},IdentificationCode:{}", dto.getAppId(), commentAppBusinessDTOS.get(0).getId(), commentAppBusinessDTOS.get(0).getIdentificationCode());
                throw new ApplicationException(ResultEnum.IDENTIFICATIONCODE_EXIST);
            }
        }
        dto.setId(id);
        boolean flag = commentAppBusinessDAO.updateById(dto.clone(CommentAppBusinessDO.class));
        //清除缓存
        RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY,id);
        return  flag;
    }

    @Override
    public Boolean saveCommentAppBusiness(CommentAppBusinessDTO dto) {
        // 标识码应用间不能相同
        CommentAppBusinessQuery query = new CommentAppBusinessQuery();
        query.setIdentificationCode(dto.getIdentificationCode());
        query.setAppId(dto.getAppId());
        List<CommentAppBusinessDTO> commentAppBusinessDTOS = this.listAllCommentAppBusiness(query);
        if (!CollectionUtil.isEmpty(commentAppBusinessDTOS)) {
            logger.error("标识码已存在：appId:{},IdentificationCode:{}", dto.getAppId(), dto.getIdentificationCode());
            throw new ApplicationException(ResultEnum.IDENTIFICATIONCODE_EXIST);
        }
        dto.setCode("0");
        // 默认不支持文本、星评、标签设置
        CommentAppBusinessSupportSetDTO supportSet = new CommentAppBusinessSupportSetDTO();
        supportSet.setSupportLabel(false);
        supportSet.setSupportStar(false);
        supportSet.setSupportText(false);
        dto.setSupportSettings(JSONObject.toJSONString(supportSet));
        CommentAppBusinessDO commentAppBusiness = dto.clone(CommentAppBusinessDO.class);
        if (commentAppBusinessDAO.save(commentAppBusiness)) {
            dto.setId(commentAppBusiness.getId());
            // 生成编码code
            String code = dto.getId() + MIN_CODE + "";
            commentAppBusiness.setCode(code);
            return commentAppBusinessDAO.updateById(commentAppBusiness);
        }
        return false;
    }


    @Override
    public Boolean deleteCommentAppBusinesss(Long... ids) {
        return commentAppBusinessDAO.removeByIds(Arrays.asList(ids));
    }

    @Override
    public List<CommentAppBusinessDTO> listAllCommentAppBusiness(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return CollectionUtil.createArrayList();
        }
        QueryWrapper<CommentAppBusinessDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<CommentAppBusinessDO> appBusinessDOS = commentAppBusinessDAO.list(queryWrapper);
        return ObjectCloneUtils.convertList(appBusinessDOS, CommentAppBusinessDTO.class);
    }

    @Override
    public CommentAppBusinessDTO getByCommentAppBusinessDTO(CommentAppBusinessDTO appBusinessDTO) throws ApplicationException {
        QueryWrapper<CommentAppBusinessDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(appBusinessDTO.clone(CommentAppBusinessDO.class));
        CommentAppBusinessDO appBusinessDO = commentAppBusinessDAO.getOne(queryWrapper);
        return Optional.ofNullable(appBusinessDO).orElseThrow(() -> new ApplicationException(ResultEnum.BUSINESS_NOT_EXIST))
                .clone(CommentAppBusinessDTO.class);
    }
}