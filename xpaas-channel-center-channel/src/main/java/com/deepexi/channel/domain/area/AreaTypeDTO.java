package com.deepexi.channel.domain.area;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
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
public class AreaTypeDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父级分类ID
     */
    private Long parentId;

    /**
     * 上级是否限制分类 0 不限制 1 限制
     */
    private Integer limitParent;

    /**
     * 上级名称
     */
    private String parentName;

    /**
     * 上级名称
     */
    private String parentNameEn;

    /**
     * 层级路径
     */
    private String path;

    /**
     * 区域分类名称
     */
    private String areaTypeName;

    /**
     * 区域分类编码
     */
    private String areaTypeCode;

    /**
     * 区域分类英文名称
     */
    private String areaTypeNameEn;

    /**
     * 描述
     */
    private String description;

}
