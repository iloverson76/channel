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
public class ChainTypeVO extends AbstractObject {

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
     * 父级分类ID
     */
    @ApiModelProperty("父节点ID")
    private Long parentId;

    /**
     * 上级分类名称
     */
    @ApiModelProperty("上级分类名称")
    private String parentName;

    /**
     * 是否限制上级 0 不限制 1 限制
     */
    @ApiModelProperty("是否限制上级 0 不限制 1 限制")
    private Integer limitParent;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty("连锁分类名称")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+$",message = "名称只能为中文、数字或英文")
    @Size(min=1,max=16)
    private String chainTypeName;

    /**
     * 连锁分类编码
     */
    @ApiModelProperty("连锁分类编码")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message="编码只能为字母和数字")
    @Size(min=1,max=16)
    private String chainTypeCode;

    /**
     * 连锁分类英文名称
     */
    @ApiModelProperty("连锁分类英文名称")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message="英文名称只能为字母和数字")
    private String chainTypeNameEn;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("节点路径 /1/10/100")
    private String path;

    @ApiModelProperty("根节点id，为0时证明不是根节点")
    private Long linkId;

}
