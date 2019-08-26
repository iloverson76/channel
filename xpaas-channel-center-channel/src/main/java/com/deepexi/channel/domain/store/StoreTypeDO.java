package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 门店类型表
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cc_store_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="storeType对象", description="门店类型表")
public class StoreTypeDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 门店类型名称
     */
    private String storeTypeName;

    /**
     * 门店类型编码
     */
    private String storeTypeCode;

    /**
     * 门店类型英文名称
     */
    private String storeTypeNameEn;

    /**
     * 描述
     */
    private String description;

}
