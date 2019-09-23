package com.deepexi.channel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/4 14:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChainTreeDTO extends ChainDTO {

    private List<ChainTreeDTO> children;
    /**
     * 连锁分类名称
     */
    private String chainTypeName;

}