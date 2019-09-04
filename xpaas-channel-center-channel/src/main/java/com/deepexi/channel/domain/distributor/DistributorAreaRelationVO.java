package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
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
@ApiModel(value="DistributorArea对象", description="经销商-区域关联表")
public class DistributorAreaRelationVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 经销商ID
     */
    @ApiModelProperty("经销商ID")
    private Long distributortId;

    /**
     * 区域ID
     */
    @ApiModelProperty("区域ID")
    private Long areaId;

}
