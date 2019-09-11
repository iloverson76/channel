package com.deepexi.channel.domain.chain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
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
@TableName("cc_chain")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="chain对象", description="连锁表")
public class ChainDO extends SuperEntity {

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
