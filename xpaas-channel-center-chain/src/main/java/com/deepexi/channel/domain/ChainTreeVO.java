package com.deepexi.channel.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("连锁树结构")
public class ChainTreeVO extends ChainVO {

    @ApiModelProperty(value = "连锁子节点")
    private List<ChainTreeVO> children;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty(value = "连锁分类名称", example = "渠道")
    private String chainTypeName;

}