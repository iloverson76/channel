package com.deepexi.channel.domain.chain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
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

   List<ChainTypeVO> ChainType;

   private Integer linkType;

}
