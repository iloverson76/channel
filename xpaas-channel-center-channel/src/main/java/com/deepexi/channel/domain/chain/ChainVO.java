package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class ChainVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    /**
     * 父节点ID
     */
    @ApiModelProperty(value = "父节点ID",example = "123")
    private Long parentId;

    /**
     * 连锁分类ID
     */
    @ApiModelProperty(value = "连锁分类ID", example = "123")
    private Long chainTypeId;

    /**
     * 连锁名称
     */
    @ApiModelProperty(value = "连锁名称", example = "家乐福")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+$")
    @Size(min=1,max=16)
    private String chainName;

    /**
     * 连锁编码
     */
    @ApiModelProperty(value = "连锁编码", example = "abcd1234")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @Size(min=1,max=16)
    private String chainCode;

    /**
     * 连锁英文名称
     */
    @ApiModelProperty(value = "连锁英文名称", example = "jialefu")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String chainNameEn;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", example = "描述")
    private String description;

    /**
     * 营业执照
     */
    @ApiModelProperty(value = "营业执照", example = "www.baidu.com")
    private String businessLicense;

    @ApiModelProperty(value = "层级关系")
    private String path;

    @ApiModelProperty(value = "其他")
    private String other;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty(value = "连锁分类名称", example = "渠道")
    private String chainTypeName;

    @ApiModelProperty(value = "分类是否限制上级")
    private Integer limitParent;

}
