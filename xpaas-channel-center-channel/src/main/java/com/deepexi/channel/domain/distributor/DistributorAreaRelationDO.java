package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("cc_distributor_area_relation")
@ApiModel(value="DistributorArea对象", description="经销商-区域关联表")
public class DistributorAreaRelationDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 经销商ID
     */
    @TableField(value = "`distributort_id`", fill = FieldFill.INSERT)
    private Long distributortId;

    /**
     * 区域ID
     */
    @TableField(value = "`area_id`", fill = FieldFill.INSERT)
    private Long areaId;

}
