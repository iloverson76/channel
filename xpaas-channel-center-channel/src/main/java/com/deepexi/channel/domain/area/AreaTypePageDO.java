package com.deepexi.channel.domain.area;

import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p>
 * 区域类型表
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
@ApiModel(value="AreaTypePage对象", description="区域类型列表")
public class AreaTypePageDO extends AreaTypeDO {

    private static final long serialVersionUID = 1L;

    /**
     * 父级分类名称
     */
    private String parentName;

}
