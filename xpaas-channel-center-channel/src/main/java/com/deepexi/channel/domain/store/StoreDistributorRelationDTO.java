package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 19:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDistributorRelationDTO extends SuperEntity {
    private Long storeId;
    private Long distributorId;
    private Long gradeSystemId;
//
}