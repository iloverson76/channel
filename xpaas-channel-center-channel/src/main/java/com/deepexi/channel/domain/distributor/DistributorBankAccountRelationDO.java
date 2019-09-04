package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("cc_distributor_back_account_relation")
@ApiModel(value="DistributorArea对象", description="经销商-银行账号关联表")
public class DistributorBankAccountRelationDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "`app_id`", fill = FieldFill.INSERT)
    @ApiModelProperty("应用ID")
    private String appId;

    @TableField(value = "`tenant_Id`", fill = FieldFill.INSERT)
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 经销商ID
     */
    @TableField(value = "`distributort_id`", fill = FieldFill.INSERT)
    private Long distributortId;

    /**
     * 银行账号ID
     */
    @TableField(value = "`bank_account_id`", fill = FieldFill.INSERT)
    private Long bankAccountId;

}
