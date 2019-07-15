package com.deepexi.promotion.validator;

import com.deepexi.promotion.domain.CommentDetailReplyInputVO;
import com.deepexi.util.CollectionUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 回复评论校验器(评论内容不能全部为空)
 * @author liaoxiaoxin
 * @date 2019/6/3 19:22
 */
public class NotAllEmptyValidator implements ConstraintValidator<NotAllEmpty, CommentDetailReplyInputVO> {

    @Override
    public boolean isValid(CommentDetailReplyInputVO value, ConstraintValidatorContext context) {
        if (CollectionUtil.isEmpty(value.getCommentResourceList()) &&
                CollectionUtil.isEmpty(value.getCommentLabelList()) &&
                CollectionUtil.isEmpty(value.getCommentStarList())) {
            return false;
        }
        return true;
    }
}
