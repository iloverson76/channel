package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
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
@TableName("cc_distributor_bank_account_relation")
@ApiModel(value="DistributorArea对象", description="经销商-银行账号关联表")
public class DistributorBankAccountRelationDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 经销商ID
     */
    private Long distributorId;

    /**
     * 银行账号ID
     */
    private Long bankAccountId;

}
