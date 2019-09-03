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
@TableName("cc_distributor_system_grade")
@ApiModel(value="DistributorGrade对象", description="经销商体系和等级关联表")
public class DistributorSystemGradeRelationDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 体系ID
     */
    private Long distributorSystemId;

    /**
     * 等级ID
     */
    private Long distributorGradeId;


}
