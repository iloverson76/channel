package com.deepexi.channel.domain.bank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.CommQuery;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * <p>
 * 银行账户表
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@TableName("cc_bank_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="BankAccount查询", description="银行账户表")
public class BankAccountQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 银行id
     */
    private Long bankId;

    private List<Long> ids;

    /**
     * 支行名称
     */
//    private String bankBranchName;

    /**
     * 银行账号
     */
//    private String accountNo;

    /**
     * 银行编码
     */
//    private String bankCode;

}
