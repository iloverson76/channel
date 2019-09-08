package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="DistributorBankAccount对象", description="经销商-银行账号关联表")
public class DistributorBankAccountRelationVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;


    /**
     * 经销商ID
     */
    @ApiModelProperty("经销商ID")
    private Long distributorId;

    /**
     * 银行账号ID
     */
    @ApiModelProperty("银行账号ID")
    private Long bankAccountId;

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
