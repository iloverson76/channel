package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @ClassName CommentBusinessReplyInputVO
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-05-28 11:00
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@ApiModel("新增回复评价模板")
public class CommentBusinessReplyInputDTO extends AbstractObject {

    @ApiModelProperty("星级评价id集合")
    private List<Long> starTemplateList;

    @ApiModelProperty("标签组id集合")
    private List<Long> labelTemplateList;

    @ApiModelProperty("文本类资源设置")
    private CommentResourceSupportSetVo supportSettings;
}
