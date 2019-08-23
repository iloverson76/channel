package com.deepexi.channel.domain;

import lombok.*;

import java.time.LocalDateTime;

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
public class AreaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    /**
     * 版本号，乐观锁
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

}
