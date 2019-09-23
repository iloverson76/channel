package com.deepexi.channel.domain;

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
public class StoreAreaQuery extends CommQuery {
    private Long  storeId;
    private Long  areaId;
    private List<Long> areaIds;

}