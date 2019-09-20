package com.deepexi.channel.domain.distributor;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 等级体系表
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
@ApiModel("经销商等级体系")
public class DistributorGradeSystemVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 挂载的等级
     */
    private List<DistributorGradeVO> grades;

    /**
     * 等级体系名称
     */
    @ApiModelProperty("等级体系名称")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+$",message = "名称只能为中文、数字或英文")
    @Size(min=1,max=16)
    private String gradeSystemName;

    /**
     * 等级体系名称-英文
     */
    @ApiModelProperty("等级体系名称")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message="英文名称只能为字母和数字")
    private String gradeSystemNameEn;

    /**
     * 等级体系编码
     */
    @ApiModelProperty("等级体系编码")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message="编码只能为字母和数字")
    private String gradeSystemCode;



    /**
     * 描述
     */
    private String description;
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
