package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

/**
 * <p>
 * 银行-账户表
 * </p>
 *
 * @author jobob
 * @since 2019-08-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("cc_chain_bank")
public class ChainBankDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 银行账户ID
     */
    private Long bankAccountId;

    /**
     * 连锁ID
     */
    private Long chainId;

}
