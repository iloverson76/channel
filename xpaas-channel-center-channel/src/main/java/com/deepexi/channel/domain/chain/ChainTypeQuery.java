package com.deepexi.channel.domain.chain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.CommQuery;
import com.deepexi.channel.domain.Pageable;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("连锁类型")
public class ChainTypeQuery extends CommQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "123")
    private Long id;
    /**
     * 品牌id数组
     */
    @ApiModelProperty("连锁类型id数组")
    private List<Long> ids;

    /**
     * 父级分类ID
     */
    @ApiModelProperty("父节点ID")
    private Long parentId;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty("连锁分类名称")
    private String chainTypeName;

    /**
     * 连锁分类编码
     */
    @ApiModelProperty("连锁分类编码")
    private String chainTypeCode;

}
