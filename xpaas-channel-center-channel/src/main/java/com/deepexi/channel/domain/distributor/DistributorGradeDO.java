package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p>
 * 经销商等级表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("cc_distributor_grade")
@ApiModel(value="DistributorGrade对象", description="经销商等级表")
public class DistributorGradeDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级分类ID
     */
    private Long parentId;

    /**
     * 是否根节点 0 是 1 否
     */
    private Boolean root;

    /**
     * 经销商等级名称
     */
    private String distributorGradeName;

    /**
     * 等级编码
     */

    private String distributorGradeCode;

    /**
     * 所属体系ID
     */
    private String gradeSystemId;

}
