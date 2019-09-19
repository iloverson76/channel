package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("门店连琐关联")
public class StoreChainQuery extends CommQuery {
    private Long stroeId;
    private Long chainId;
    private List<Long> chainIds;

}