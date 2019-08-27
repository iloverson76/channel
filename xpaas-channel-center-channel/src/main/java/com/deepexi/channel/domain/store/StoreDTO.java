package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 门店等级表
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 门店等级名称
     */
    private String storeGradeName;

    /**
     * 门店等级编码
     */
    private String storeGradeCode;

    /**
     * 门店等级英文名称
     */
    private String storeGradeNameEn;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否启用 0 禁用 1 启用
     */
    private Boolean enable;

}
