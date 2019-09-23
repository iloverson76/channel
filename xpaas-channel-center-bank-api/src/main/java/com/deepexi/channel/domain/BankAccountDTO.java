package com.deepexi.channel.domain;

import com.deepexi.channel.domain.SuperEntity;
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
public class BankAccountDTO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 银行id
     */
    private Long bankId;

    /**
     * 支行名称
     */
    private String bankBranchName;

    /**
     * 银行账号
     */
    private String accountNo;

    /**
     * 银行编码
     */
    private String bankCode;

    private String bankName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}
