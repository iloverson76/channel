package com.deepexi.channel.domain;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * <p>
 * 等级体系表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("供应商等级体系查询")
public class DistributorGradeSystemQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 等级体系名称
     */
    @ApiModelProperty("等级体系名称")
    private String gradeSystemName;

    /**
     * 等级体系编码
     */
    @ApiModelProperty("等级体系编码")
    private String gradeSystemCode;

    @ApiModelProperty(value = "主键列表", example = "123")
    private List<Long> ids;

    @ApiModelProperty(value = "主键", example = "123")
    private Long id;
}
