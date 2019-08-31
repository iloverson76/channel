package com.deepexi.channel.domain.area;

import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

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
@ApiModel("区域类型")
public class AreaTypeDelVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    /**
     * 批量删除接口参数.
     */
    @ApiModelProperty("id,parentId")
    private List<Map<Long,Long>> deletedIds;


}
