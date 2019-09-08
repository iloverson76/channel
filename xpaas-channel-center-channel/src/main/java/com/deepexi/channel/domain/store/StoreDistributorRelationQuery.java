package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.CommQuery;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 19:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class StoreDistributorRelationQuery extends CommQuery {
    @TableField(value = "`store_id`")
    private Long storeId;
    @TableField(value = "`distributor_id`")
    private Long distributorId;
    @TableField(value = "`grade_system_id`")
    private Long gradeSystemId;
//
}