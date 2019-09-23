package com.deepexi.channel.domain;

import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

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
public class StoreGradeDTO extends SuperEntity {

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

}
