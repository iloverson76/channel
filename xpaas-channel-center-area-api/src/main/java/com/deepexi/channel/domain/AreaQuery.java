package com.deepexi.channel.domain;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * <p>
 * 区域查询
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("区域查询")
public class AreaQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    private List<Long> ids;

    private List<Long> areatypeIds;

    /**
     * 区域名称
     */
    @ApiModelProperty("区域名称")
    private String areaName;

    /**
     * 区域编码
     */
    @ApiModelProperty("区域编码")
    private String areaCode;

    /**
     * 区域分类ID
     */
    @ApiModelProperty("区域分类ID")
    private Long areaTypeId;
}
