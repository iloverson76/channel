package com.deepexi.channel.domain.bank;

import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 银行账户表
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
@ApiModel("银行账户")
public class BankAccountVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 银行id
     */
    @ApiModelProperty(value = "银行id")
    private Long bankId;

    /**
     * 支行名称
     */
    @ApiModelProperty(value = "支行名称")
    private String bankBranchName;

    /**
     * 银行账号
     */
    @ApiModelProperty(value = "银行账号")
    private String accountNo;

    /**
     * 银行编码
     */
    @ApiModelProperty(value = "银行编码")
    private String bankCode;

    @ApiModelProperty(value = "银行名称,例如建设银行")
    private String bankName;

}
