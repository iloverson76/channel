package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:41
 */
@EqualsAndHashCode(callSuper = true)
@TableName("cc_store_area")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreAreaDO  extends SuperEntity {
    @TableField(value = "`store_id`")
    private Long  storeId;

    @TableField(value = "`area_id`")
    private Long  areaId;

}