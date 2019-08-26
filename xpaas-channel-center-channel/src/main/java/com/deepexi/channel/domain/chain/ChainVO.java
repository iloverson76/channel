package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
public class ChainVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父节点ID
     */
    @ApiModelProperty("父节点ID")
    private Long parentId;

    /**
     * 父节点名称
     */
    @ApiModelProperty("父节点名称")
    private String parentName;

    /**
     * 连锁分类ID
     */
    @ApiModelProperty("连锁分类ID")
    private Long chainTypeId;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty("连锁分类名称")
    private String chainTypeName;

    /**
     * 连锁名称
     */
    @ApiModelProperty("连锁名称")
    private String chainName;

    /**
     * 连锁编码
     */
    @ApiModelProperty("连锁编码")
    private String chainCode;

    /**
     * 连锁英文名称
     */
    @ApiModelProperty("连锁英文名称")
    private String chainNameEn;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 营业执照
     */
    @ApiModelProperty("营业执照")
    private String businessLicense;

}
