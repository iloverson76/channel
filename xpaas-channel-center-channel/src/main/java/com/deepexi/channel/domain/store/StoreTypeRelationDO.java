package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@TableName("cc_store_type_relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "门店-等级关联表")
public class StoreTypeRelationDO extends SuperEntity {
    @TableField(value = "`store_id`")
    private Long  storeId;
    @TableField(value = "`store_type_id`")
    private Long  storeTypeId;
}
