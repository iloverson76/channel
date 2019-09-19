package com.deepexi.channel.domain.bank;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import lombok.*;

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
@Builder
@ApiModel("银行")
public class BankQuery extends CommQuery {
    private List<Long> ids;
    private String bankName;
}