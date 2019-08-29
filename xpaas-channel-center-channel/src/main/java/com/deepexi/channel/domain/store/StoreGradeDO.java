package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 门店等级表
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@TableName("cc_store_grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="storeGrade对象", description="门店等级表")
public class StoreGradeDO extends BaseEntity {

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
