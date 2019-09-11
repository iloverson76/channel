package com.deepexi.channel.domain.distributor;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

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
public class DistributorGradeRelationDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 经销商ID
     */
    @ApiModelProperty("经销商ID")
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

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
