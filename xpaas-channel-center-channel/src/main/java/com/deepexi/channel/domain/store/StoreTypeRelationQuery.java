package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreTypeRelationQuery extends SuperEntity {

    private Long storeId;

    private Long storeTypeId;

    private List<Long> storeTypeIds;
}
