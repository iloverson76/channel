package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 门店信息表
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cc_store")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="store对象", description="门店表")
public class StoreDO  extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 门店英文名称
     */
    private String storeNameEn;

    /**
     * 客户名称
     */
    private String clientName;

    /**
     * 客户编码
     */
    private String clientCode;

    /**
     * 门店地址
     */
    private String storeAddress;

    /**
     * 门店图片
     */
    private String storePhone;

    /**
     * 状态 1 启用 2 禁用
     */
    private Boolean status;

}
