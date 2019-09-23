package com.deepexi.channel.domain;

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
@ApiModel(value="DistributorGradeRelation对象", description="经销商-等级-体系关联表")
public class DistributorGradeRelationDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 经销商ID
     */
    private Long distributorId;

    /**
     * 等级ID
     */
    private Long gradeId;
    /**
     * 体系ID
     */
    private Long systemId;
    /**
     * 是否限制上级
     */
    private Integer limitedParent;
    /**
     * 指定的上级(一个)
     */
    private Long parentId;

}
