package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.CommQuery;
import com.deepexi.channel.domain.Pageable;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * <p>
 * 连锁表
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
@ApiModel("连锁")
public class ChainQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "123")
    private Long id;

    /**
     * 父节点ID
     */
    @ApiModelProperty(value= "父节点ID",example = "123")
    private Long parentId;

    /**
     * 连锁分类ID
     */
    @ApiModelProperty(value = "连锁分类ID",example = "123")
    private Long chainTypeId;

    /**
     * 连锁名称
     */
    @ApiModelProperty(value = "连锁名称",example = "家乐福")
    private String chainName;

    /**
     * 连锁编码
     */
    @ApiModelProperty(value = "连锁编码",example = "abcd123")
    private String chainCode;

}
