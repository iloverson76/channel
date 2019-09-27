package com.deepexi.channel.domain;

import com.deepexi.channel.domain.BankAccountVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import java.util.List;
/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("连锁详情")
public class ChainDetailVO extends ChainVO {
    @ApiModelProperty("银行账号列表")
    @Valid
    private List<BankAccountVO> bankAccountList;

    /**
     * 父节点名称
     */
    @ApiModelProperty(value = "连锁父级名称")
    private String parentName;

    @ApiModelProperty(value = "上级节点分类id")
    private Long parentChainTypeId;
}
