package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreChainDTO extends SuperEntity {
    @TableField(value = "`store_id`")
    private Integer  storeId;
    @TableField(value = "`chain_id`")
    private Integer  chainId;

}
