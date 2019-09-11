package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 连锁类型表
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("连锁类型")
public class ChainTypeQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "123")
    private Long id;
    /**
     * 连琐类型id数组
     */
    @ApiModelProperty("连锁类型id数组")
    private List<Long> ids;
    /**
     * 连琐类型id数组
     */
    @ApiModelProperty("不包含这些id的连琐类型")
    private List<Long> excludeIds;

    /**
     * 父级分类ID
     */
    @ApiModelProperty("父节点ID")
    private Long parentId;

    /**
     * 是否限制上级 0 不限制 1 限制
     */
    @ApiModelProperty("是否限制上级 0 不限制 1 限制")
    private Integer limitParent;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty("连锁分类名称，用作模糊查询")
    private String chainTypeName;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty("连锁分类名称,用作精确查询")
    private String chainTypeAccuracyName;

    /**
     * 连锁分类编码
     */
    @ApiModelProperty("连锁分类编码, 用作模糊查询")
    private String chainTypeCode;

    /**
     * 连锁分类编码
     */
    @ApiModelProperty("连锁分类编码， 用作精准查询")
    private String chainTypeAccuracyCode;

    @ApiModelProperty("节点路径 /1/10/100")
    private String path;

    @ApiModelProperty("根节点id，为0时证明不是根节点")
    private Long linkId;

}
