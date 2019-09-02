package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.bank.BankAccountVO;
import com.deepexi.channel.domain.chain.ChainVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("连锁详情")
public class ChainDetailVO extends ChainVO {
    @ApiModelProperty("银行账号列表")
    private List<BankAccountVO> bankAccountList;

    @ApiModelProperty(value = "连锁子节点")
    private List<ChainVO> children;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty(value = "连锁分类名称", example = "渠道")
    private String chainTypeName;

    /**
     * 父节点名称
     */
    @ApiModelProperty(value = "连锁父级名称")
    private String parentName;


}
