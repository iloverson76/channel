package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@TableName("cc_store_chain")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreChainDO extends SuperEntity {
    // @ApiModelProperty(value = "门店Id")
    @TableField(value = "`store_id`")
    private Long  storeId;
    // @ApiModelProperty(value = "连锁ID")
    @TableField(value = "`chain_id`")
    private Long  chainId;

}
