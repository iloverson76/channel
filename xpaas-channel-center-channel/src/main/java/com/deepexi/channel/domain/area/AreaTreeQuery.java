package com.deepexi.channel.domain.area;

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
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("区域层级元素列表和树形结构查询")
public class AreaTreeQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 区域分类名称
     */
    private String areaTypeName;

}
