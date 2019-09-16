package com.deepexi.channel.domain.area;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

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
@TableName("cc_area_type")
@ApiModel(value="AreaType对象", description="区域类型表")
public class AreaTypeDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    private Long linkId;

    /**
     * 父级分类ID
     */
    private Long parentId;

    /**
     * 上级是否限制分类 0 不限制 1 限制
     */
    private Integer limitParent;

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
