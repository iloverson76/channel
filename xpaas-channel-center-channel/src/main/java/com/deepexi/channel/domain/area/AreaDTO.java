package com.deepexi.channel.domain.area;

import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

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
public class AreaDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 区域分类ID
     */
    private Integer areaTypeId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 区域英文名称
     */
    private String areaNameEn;

    /**
     * 描述
     */
    private String description;

}
