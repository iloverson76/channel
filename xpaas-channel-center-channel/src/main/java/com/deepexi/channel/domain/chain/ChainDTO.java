package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.SuperEntity;
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
public class ChainDTO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 连锁分类ID
     */
    private Long chainTypeId;

    /**
     * 连锁名称
     */
    private String chainName;

    /**
     * 连锁编码
     */
    private String chainCode;

    /**
     * 连锁英文名称
     */
    private String chainNameEn;

    /**
     * 描述
     */
    private String description;

    /**
     * 营业执照
     */
    private String businessLicense;

    private String path;

    private String other;
}
