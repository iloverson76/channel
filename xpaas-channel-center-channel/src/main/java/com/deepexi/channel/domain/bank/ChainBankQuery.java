package com.deepexi.channel.domain.bank;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/20 10:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("连琐银行账号关联")
public class ChainBankQuery extends CommQuery {
    private Long bankAccountId;
    private Long chainId;
}