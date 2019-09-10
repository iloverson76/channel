package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("cc_distributor")
@ApiModel(value="Distributor对象", description="经销商实体表")
public class DistributorDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 是否指定上级
     */
    @ApiModelProperty("是否指定上级 0 否 1 是")
    private int limitedParent;

    /**
     * 上级经销商ID
     */
    @ApiModelProperty("直接上级经销商ID")
    private Long parentId;

    /**
     * 经销商类型 1 厂商 2 经销商
     */
    private Integer distributorType;

    /**
     * 经销商编码
     */
    private String distributorCode;

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
     * 地址
     */
    private String address;

    /**
     * 状态 0 禁用 1 启用
     */
    private Integer enable ;

}
