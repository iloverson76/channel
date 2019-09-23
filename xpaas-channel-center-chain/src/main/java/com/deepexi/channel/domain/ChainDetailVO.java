package com.deepexi.channel.domain;

import com.deepexi.channel.domain.BankAccountVO;
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

    /**
     * 父节点名称
     */
    @ApiModelProperty(value = "连锁父级名称")
    private String parentName;

    @ApiModelProperty(value = "上级节点分类id")
    private Long parentChainTypeId;
}
