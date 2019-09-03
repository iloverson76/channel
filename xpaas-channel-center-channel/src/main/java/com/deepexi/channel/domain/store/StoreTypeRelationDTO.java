package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deepexi.channel.domain.BaseEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreTypeRelationDTO extends BaseEntity {
    @TableField(value = "`store_id`")
    private Long  storeId;
    @TableField(value = "`store_type_Id`")
    private Long  storeTypeId;
}
