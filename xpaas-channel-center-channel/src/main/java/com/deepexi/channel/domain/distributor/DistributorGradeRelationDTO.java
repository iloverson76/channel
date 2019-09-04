package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="DistributorGradeRelation对象", description="经销商-等级关联表")
public class DistributorGradeRelationDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 经销商ID
     */
    @ApiModelProperty("经销商ID")
    private Long distributortId;

    /**
     * 等级ID
     */
    @ApiModelProperty("等级ID")
    private Long distributorGradeId;

}
