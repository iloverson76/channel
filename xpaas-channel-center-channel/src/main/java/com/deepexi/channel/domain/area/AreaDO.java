package com.deepexi.channel.domain.area;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

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
@TableName("cc_area")
@ApiModel(value="Area对象", description="区域表")
public class AreaDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    private Integer root;//是否根节点

    /**
     * 父节点ID
     */
    private Long parentId;

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

}
