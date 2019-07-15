package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author liaoxiaoxin
 * @date 2019/5/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentCheckStatusVO extends AbstractObject {

    @NotEmpty(message = "评价明细ID不能为空")
    private List<Long> detailIds;
}
