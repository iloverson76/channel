package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 模板返回结果集
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CommentTemplateAddGroupVO extends AbstractObject {

    /**
     * 业务对象关联主键
     */
    @NotNull
    private Long businessModelConnectId;

    /**
     * 标签组列表
     */
    @NotEmpty
    private List<Long> labelGroupIdList;

}

