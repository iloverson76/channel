package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountVO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
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
@ApiModel(value="经销商")
public class DistributorVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("所属等级列表")
    private List<DistributorGradeVO> grades;

    @ApiModelProperty("所属等级ID")
    private List<Long> gradeIds;

    @ApiModelProperty("银行账号列表")
    private List<BankAccountVO> bankAccounts;

    @ApiModelProperty("银行账号ID")
    private List<Long> bankAccountIds;

    @ApiModelProperty("所在区域")
    private AreaDTO area;

    @ApiModelProperty("所在区域ID")
    private long AreaId;

    /**
     * 是否指定上级
     */
    @ApiModelProperty("是否指定上级 0 否 1 是")
    private int limitedParent;

    /**
     * 上级经销商ID
     */
    @ApiModelProperty("上级经销商ID")
    private int parent_id;

    /**
     * 经销商类型 1 厂商 2 经销商
     */
    @ApiModelProperty("经销商类型 1经销商 2 厂商")
    private Integer distributorType;

    /**
     * 经销商编码
     */
    private String distributorCode;

    /**
     * 经销商名称
     */
    @ApiModelProperty("经销商中文名称")
    private String distributorName;

    /**
     * 经销商英文名称
     */
    @ApiModelProperty("经销商英文名称")
    private String distributorNameEn;

    /**
     * 营业类型
     */
    @ApiModelProperty("营业类型")
    private String businessType;

    /**
     * 营业执照号码
     */
    @ApiModelProperty("营业执照号码(多个值用逗号分隔)")
    private String businessLicenseNo;

    /**
     * 营业执照图片
     */
    @ApiModelProperty("营业执照图片(url,多个值用逗号分隔)")
    private String businessLicense;

    /**
     * 信用值
     */
    @ApiModelProperty("信用值")
    private Integer creditValue;

    /**
     * 负责人名称
     */
    @ApiModelProperty("负责人名称")
    private String principalName;

    /**
     * 负责人身份证号
     */
    @ApiModelProperty("负责人身份证号")
    private String principalIdCard;

    /**
     * 资格证明
     */
    @ApiModelProperty("资格证明")
    private String qualification;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 状态 0 禁用 1 启用
     */
    @ApiModelProperty("状态 0 禁用 1 启用")
    private Integer enable ;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
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
