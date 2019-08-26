package com.deepexi.channel.domain.area;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域类型表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cc_area_type")
public class AreaTypeVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级分类ID
     */
    private Long parentId;

    /**
     * 是否限制上级 0 不限制 1 限制
     */
    private Boolean limitParent;

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
