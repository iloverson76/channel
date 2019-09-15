package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 20:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDistributorDO extends SuperEntity {
    @TableField(value = "`store_id`")
    Long storeId;
    @TableField(value = "`distributor_id`")
    Long distributorId;
    /**经销商code*/
    String distributorCode;
    /**经销商中文名称*/
    String distributorName;
    /**上级经销商ID*/
    @TableField(value = "`parent_id`")
    Long parentId;
    /**上级经销商名称*/
    String parentName;
    /**上级经销商code*/
    String parentCode;
    /**经销商等级id*/
    @TableField(value = "`grade_system_id`")
    Long gradeSystemId;
    /**经销商编码*/
    String distributorGradeSystemCode;
    /**进销商等级体系名称*/
    String distributorGradeSystemName;
    /**上级经销商*/
//    private List<StoreDistributorDO> parentDistributor;
}