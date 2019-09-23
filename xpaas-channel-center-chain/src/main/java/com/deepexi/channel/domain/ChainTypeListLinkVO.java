package com.deepexi.channel.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * <p>
 * 连锁类型表
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("连锁类型")
public class ChainTypeListLinkVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("连锁list")
    List<ChainTypeVO> ChainType;

    @ApiModelProperty("连锁链路数")
    private Integer linkType;

}
