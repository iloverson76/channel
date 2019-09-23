package com.deepexi.channel.domain;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * <p>
 * 连锁表
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("连锁")
public class ChainQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "主键列表")
    private List<Long> ids;

    /**
     * 父节点ID
     */
    @ApiModelProperty(value= "父节点ID")
    private Long parentId;

    /**
     * 连锁分类ID
     */
    @ApiModelProperty(value = "连锁分类ID")
    private Long chainTypeId;

    /**
     * 连锁名称
     */
    @ApiModelProperty(value = "连锁名称")
    private String chainName;

    /**
     * 连锁编码
     */
    @ApiModelProperty(value = "连锁编码，用于模糊查询")
    private String chainCode;

    @ApiModelProperty(value = "连锁层级路径，连琐树用于判断是否是编辑还是新增，path为空就需要新增，不为空为编辑")
    private String path;

    @ApiModelProperty(value = "连锁类型id列表，前端不用理")
    private List<Long> chainTypeIdList;

    @ApiModelProperty(value = "连琐精准编码，用于精准查询")
    private String chainAccuracyCode;
    @ApiModelProperty(value = "连琐精准名称，用于精准查询")
    private String chainAccuracyName;
}
