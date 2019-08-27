package com.deepexi.channel.domain.area;

import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.CommQuery;
import com.deepexi.channel.domain.Pageable;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * <p>
 * 区域类型查询
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("区域类型查询")
public class AreaTypeQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 区域分类名称
     */
    @ApiModelProperty("区域分类名称")
    private String areaTypeName;

    /**
     * 区域分类编码
     */
    @ApiModelProperty("区域分类编码")
    private String areaTypeCode;
}
