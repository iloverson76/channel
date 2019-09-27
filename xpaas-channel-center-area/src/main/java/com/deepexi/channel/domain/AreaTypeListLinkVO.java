package com.deepexi.channel.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * <p>
 * 区域类型表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("区域类型")
public class AreaTypeListLinkVO extends AbstractObject {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("区域list")
    List<AreaTypeBusiVO> areaType;

    @ApiModelProperty("区域链路数")
    private Integer linkType;

}
