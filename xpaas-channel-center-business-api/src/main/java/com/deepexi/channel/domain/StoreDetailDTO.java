package com.deepexi.channel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDetailDTO extends StoreDTO {
    private StoreTypeDTO storeTypeDTO;

    private StoreGradeDTO storeGradeDTO;

    private List<AreaDTO> areaDTOS;

    private List<ChainDetailDTO> chainDTOS;

    private List<StoreDistributorDTO> storeDistributorDTOS;

    private List<StoreHistoryDTO> storeHistoryDTOS;
}
