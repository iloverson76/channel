package com.deepexi.channel.domain;

import com.deepexi.channel.domain.CommQuery;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

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
@ApiModel("树形结构查询")
public class AreaTreeQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 树展开层级
     */
    private Integer level;

    private List<Long> areaTypeIds;

    private Long areaId;

}
