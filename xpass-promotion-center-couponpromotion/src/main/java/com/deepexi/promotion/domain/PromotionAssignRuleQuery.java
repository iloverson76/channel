package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author zhoust
 * @date 2019/7/19
 **/
@Data
public class PromotionAssignRuleQuery extends AbstractObject implements Pageable{

    @Min(value = -1,message = "page最小为-1")
    @Builder.Default
    private Integer page = -1;

    @Min(value = 0,message = "size最小为0")
    @Builder.Default
    private Integer size = 10;

    @NotEmpty(message = "应用Id不能为空")
    private String appId;

    private String ruleName;

    private String ruleCode;

    private String parameterCode;

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getSize() {
        return size;
    }

}
