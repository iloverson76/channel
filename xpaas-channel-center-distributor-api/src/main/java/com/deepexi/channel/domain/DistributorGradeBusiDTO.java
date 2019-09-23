package com.deepexi.channel.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@ApiModel(value="DistributorGradeList对象", description="经销商等级列表")
public class DistributorGradeBusiDTO extends DistributorGradeDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 所属体系
     */
    private DistributorGradeSystemDTO system;

}
