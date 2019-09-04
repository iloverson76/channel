package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
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
@TableName("cc_distributor_grade_relation")
@ApiModel(value="DistributorGradeRelation对象", description="经销商-等级关联表")
public class DistributorGradeRelationDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 经销商ID
     */
    @TableField(value = "`distributort_id`", fill = FieldFill.INSERT)
    private Long distributortId;

    /**
     * 区域ID
     */
    @TableField(value = "`distributor_grade_id`", fill = FieldFill.INSERT)
    private Long distributorGradeId;

}
