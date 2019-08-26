package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

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
public class DistributorGradeDTO extends BaseEntity {

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
     * 经销商等级编码
     */
    private String distributorGradeCode;

    /**
     * 等级体系ID
     */
    private Long gradeSystemId;

}
