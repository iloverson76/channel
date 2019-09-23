package com.deepexi.channel.domain;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * <p>
 * 银行账户表
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
@ApiModel("银行账户")
public class BankAccountVO extends AbstractObject {

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
     * 银行id
     */
    @ApiModelProperty(value = "银行id")
    private Long bankId;

    /**
     * 支行名称
     */
    @ApiModelProperty(value = "支行名称")
    private String bankBranchName;

    /**
     * 银行账号
     */
    @ApiModelProperty(value = "银行账号")
    private String accountNo;

    /**
     * 银行编码
     */
    @ApiModelProperty(value = "银行编码")
    private String bankCode;

    @ApiModelProperty(value = "银行名称,例如建设银行")
    private String bankName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}
