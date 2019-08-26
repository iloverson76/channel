package com.deepexi.channel.domain.area;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cc_area")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="Area对象", description="区域表")
public class AreaDO extends BaseEntity {

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

}
