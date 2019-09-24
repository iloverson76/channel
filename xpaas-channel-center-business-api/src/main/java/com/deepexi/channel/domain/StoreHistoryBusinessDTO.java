package com.deepexi.channel.domain;

import lombok.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/24 21:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreHistoryBusinessDTO extends StoreHistoryDTO{
    private List<StoreHistoryDTO> storeHistoryDTOS;
}