package com.deepexi.channel.domain;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreAreaDTO extends SuperEntity {
    private Long  storeId;

    private Long  areaId;

}