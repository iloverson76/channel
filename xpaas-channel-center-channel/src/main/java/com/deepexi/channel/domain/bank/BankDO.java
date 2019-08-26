package com.deepexi.channel.domain.bank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 银行账户表
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cc_bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="Bank对象", description="银行账户表")
public class BankDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 银行名称
     */
    private String bankName;

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

}
