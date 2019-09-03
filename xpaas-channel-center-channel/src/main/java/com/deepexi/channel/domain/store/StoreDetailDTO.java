package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.distributor.DistributorDTO;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDetailDTO extends StoreDTO {
    private StoreTypeDTO storeTypeDTO;

    private StoreGradeDTO storeGradeDTO;

    private AreaDTO areaDTO;

    private ChainDTO chainDTO;

    private List<DistributorDTO> distributorDTOS;
}
