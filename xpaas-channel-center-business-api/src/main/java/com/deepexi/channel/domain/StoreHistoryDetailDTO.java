package com.deepexi.channel.domain;

import lombok.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/24 16:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreHistoryDetailDTO extends SuperEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 原门店id
     */
    private Long storeId;
    /**
     * 修改历史版本号
     */
    private String versionNumber;
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
     * 是否启用 0 禁用 1 启用
     */
    private Integer enable;

    private StoreTypeDTO storeTypeDTO;

    private StoreGradeDTO storeGradeDTO;

    private List<AreaDTO> areaDTOS;

    private List<ChainDetailDTO> chainDTOS;

    private List<StoreDistributorDTO> storeDistributorDTOS;

    private List<StoreHistoryDTO> storeHistoryDTOS;
}