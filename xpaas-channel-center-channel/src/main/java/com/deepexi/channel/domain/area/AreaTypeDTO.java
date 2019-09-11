package com.deepexi.channel.domain.area;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

import java.util.Date;
import java.util.List;

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

    private Integer root;

    private Long chainId;


    /**
     * 挂载的区域
     */
    private List<AreaDTO> areas;

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

    private String parentCode;

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
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;


}
