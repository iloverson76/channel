package com.deepexi.channel.domain.area;

import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

import java.util.Date;

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

    private AreaTypeDTO areaType;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 路径
     */
    private String path;

    /**
     * 区域分类ID
     */
    private Long areaTypeId;

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
