package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 连锁类型表
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
public class ChainTypeDTO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级分类ID
     */
    private Long parentId;

    /**
     * 父级分类名称
     */
    private String parentName;

    /**
     * 是否限制上级 0 不限制 1 限制
     */
    private Integer limitParent;

    /**
     * 连锁分类名称
     */
    private String chainTypeName;

    /**
     * 连锁分类编码
     */
    private String chainTypeCode;

    /**
     * 连锁分类英文名称
     */
    private String chainTypeNameEn;

    /**
     * 描述
     */
    private String description;
    /**
     *    节点路径
     */
    private String path;

    /**
     * 根节点id，为0时证明不是根节点
     */
    private Long linkId;

}
