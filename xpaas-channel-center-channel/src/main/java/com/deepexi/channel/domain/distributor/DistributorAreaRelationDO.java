package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
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
@TableName("cc_distributor_area_relation")
@ApiModel(value="DistributorArea对象", description="经销商-区域关联表")
public class DistributorAreaRelationDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 经销商ID
     */
    private Long distributorId;

    /**
     * 区域ID
     */
    @TableField(value = "`area_id`", fill = FieldFill.INSERT)
    private Long areaId;

    /**
     * 描述
     */
    private String description;
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
