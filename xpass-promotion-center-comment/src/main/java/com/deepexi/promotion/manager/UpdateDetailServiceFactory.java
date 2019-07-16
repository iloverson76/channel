package com.deepexi.promotion.manager;

import com.deepexi.promotion.domain.CommentLabelTemplateDTO;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.UpdateDetailService;
import com.deepexi.promotion.service.LabelTemplateUpdateDetailServiceImpl;
import com.deepexi.util.extension.ApplicationException;

/**
 * @author zhoust
 * @date 2019/5/20
 **/
public class UpdateDetailServiceFactory {
    public static UpdateDetailService getService(Class clazz){

        if (clazz.equals(CommentLabelTemplateDTO.class))
            return new LabelTemplateUpdateDetailServiceImpl();
        throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
    }
}
