package com.deepexi.channel.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

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
@ApiModel(value="DistributorArea对象", description="经销商-银行账号关联表")
public class DistributorBankAccountRelationDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 经销商ID
     */
    private Long distributorId;

    /**
     * 银行账号ID
     */
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
