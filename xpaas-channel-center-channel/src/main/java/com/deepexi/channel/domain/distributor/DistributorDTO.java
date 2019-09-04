package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 经销商表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="Distributor对象", description="经销商实体表")
public class DistributorDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 经销商类型 1 厂商 2 经销商
     */
    private Integer distributorType;

    /**
     * 经销商名称
     */
    private String distributorName;

    /**
     * 经销商英文名称
     */
    private String distributorNameEn;

    /**
     * 营业类型
     */
    private String businessType;

    /**
     * 营业执照号码
     */
    private String businessLicenseNo;

    /**
     * 营业执照图片
     */
    private String businessLicense;

    /**
     * 信用值
     */
    private Integer creditValue;

    /**
     * 负责人名称
     */
    private String principalName;

    /**
     * 负责人身份证号
     */
    private String principalIdCard;

    /**
     * 资格证明
     */
    private String qualification;

    /**
     * 状态 0 禁用 1 启用
     */
    private Integer enable ;

    /**
     * 地址
     */
    private String address;

    /**
     * 等级
     */
    private List<DistributorGradeDTO> grades;

    /**
     * 银行账号
     */
    private List<BankAccountDTO> bankAccounts;




}
