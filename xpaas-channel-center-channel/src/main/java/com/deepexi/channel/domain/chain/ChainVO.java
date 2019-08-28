package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.bank.BankAccountVO;
import com.deepexi.channel.domain.bank.BankVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
import java.util.Set;

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
    @ApiModelProperty(value = "父节点ID",example = "123")
    private Long parentId;

    /**
     * 连锁分类ID
     */
    @ApiModelProperty(value = "连锁分类ID", example = "123")
    private Long chainTypeId;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty(value = "连锁分类名称", example = "渠道")
    private String chainTypeName;

    /**
     * 连锁名称
     */
    @ApiModelProperty(value = "连锁名称", example = "家乐福")
    private String chainName;

    /**
     * 连锁编码
     */
    @ApiModelProperty(value = "连锁编码", example = "abcd1234")
    private String chainCode;

    /**
     * 连锁英文名称
     */
    @ApiModelProperty(value = "连锁英文名称", example = "jialefu")
    private String chainNameEn;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", example = "描述")
    private String description;

    /**
     * 营业执照
     */
    @ApiModelProperty(value = "营业执照", example = "www.baidu.com")
    private String businessLicense;

    @ApiModelProperty("银行账号列表")
    private List<BankAccountVO> bankAccountList;

    @ApiModelProperty(value = "连锁子节点")
    private List<ChainVO> children;
}
