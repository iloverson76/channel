package com.deepexi.channel.domain.bank;

import com.deepexi.channel.domain.BaseEntity;
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
public class BankAccountDTO extends BaseEntity {

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

}
