package com.deepexi.channel.domain.eo;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Collection;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
* @desc cc_distributor
* @author admin
*/
//@ApiModel(description = "经销商表")
public class CcDistributor{

// @ApiModelProperty(value = "ID")
    @TableId
    @TableField(value = "`id`")
    private Integer  id;
// @ApiModelProperty(value = "经销商类型 1 厂商 2 经销商")
    @TableField(value = "`distributor_type`")
    private Boolean distributorType;
// @ApiModelProperty(value = "经销商名称")
    @TableField(value = "`distributor_name`")
    private String distributorName;
// @ApiModelProperty(value = "经销商英文名称")
    @TableField(value = "`distributor_name_en`")
    private String distributorNameEn;
// @ApiModelProperty(value = "经销商编码")
    @TableField(value = "`distributor_code`")
    private String distributorCode;
// @ApiModelProperty(value = "营业类型")
    @TableField(value = "`business_type`")
    private String businessType;
// @ApiModelProperty(value = "营业执照号码")
    @TableField(value = "`business_license_no`")
    private String businessLicenseNo;
// @ApiModelProperty(value = "营业执照图片")
    @TableField(value = "`business_license`")
    private String businessLicense;
// @ApiModelProperty(value = "信用值")
    @TableField(value = "`credit_value`")
    private Integer  creditValue;
// @ApiModelProperty(value = "负责人名称")
    @TableField(value = "`principal_name`")
    private String principalName;
// @ApiModelProperty(value = "负责人身份证号")
    @TableField(value = "`principal_id_card`")
    private String principalIdCard;
// @ApiModelProperty(value = "资格证明")
    @TableField(value = "`qualification`")
    private String qualification;
// @ApiModelProperty(value = "地址")
    @TableField(value = "`address`")
    private String address;
// @ApiModelProperty(value = "是否启用 0 禁用 1 启用")
    @TableField(value = "`enable`")
    private Boolean enable;
// @ApiModelProperty(value = "版本号，乐观锁")
    @TableField(value = "`version`")
    private Integer  version;
// @ApiModelProperty(value = "备注")
    @TableField(value = "`remark`")
    private String remark;
// @ApiModelProperty(value = "删除标记")
    @TableLogic
    @TableField(value = "`dr`")
    private Boolean dr;
// @ApiModelProperty(value = "创建人")
    @TableField(value = "`created_by`")
    private String createdBy;
// @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "`created_time`")
    private Date createdTime;
// @ApiModelProperty(value = "最后更新人")
    @TableField(value = "`updated_by`")
    private String updatedBy;
// @ApiModelProperty(value = "最后更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "`updated_time`")
    private Date updatedTime;
// @ApiModelProperty(value = "应用ID")
    @TableField(value = "`app_id`")
    private Integer  appId;
// @ApiModelProperty(value = "租户id")
    @TableField(value = "`tenant_id`")
    private String tenantId;

    public void setId(Integer  id){
    this.id = id;
    }

    public Integer  getId(){
    return this.id;
    }

    public void setDistributorType(Boolean distributorType){
    this.distributorType = distributorType;
    }

    public Boolean getDistributorType(){
    return this.distributorType;
    }

    public void setDistributorName(String distributorName){
    this.distributorName = distributorName;
    }

    public String getDistributorName(){
    return this.distributorName;
    }

    public void setDistributorNameEn(String distributorNameEn){
    this.distributorNameEn = distributorNameEn;
    }

    public String getDistributorNameEn(){
    return this.distributorNameEn;
    }

    public void setDistributorCode(String distributorCode){
    this.distributorCode = distributorCode;
    }

    public String getDistributorCode(){
    return this.distributorCode;
    }

    public void setBusinessType(String businessType){
    this.businessType = businessType;
    }

    public String getBusinessType(){
    return this.businessType;
    }

    public void setBusinessLicenseNo(String businessLicenseNo){
    this.businessLicenseNo = businessLicenseNo;
    }

    public String getBusinessLicenseNo(){
    return this.businessLicenseNo;
    }

    public void setBusinessLicense(String businessLicense){
    this.businessLicense = businessLicense;
    }

    public String getBusinessLicense(){
    return this.businessLicense;
    }

    public void setCreditValue(Integer  creditValue){
    this.creditValue = creditValue;
    }

    public Integer  getCreditValue(){
    return this.creditValue;
    }

    public void setPrincipalName(String principalName){
    this.principalName = principalName;
    }

    public String getPrincipalName(){
    return this.principalName;
    }

    public void setPrincipalIdCard(String principalIdCard){
    this.principalIdCard = principalIdCard;
    }

    public String getPrincipalIdCard(){
    return this.principalIdCard;
    }

    public void setQualification(String qualification){
    this.qualification = qualification;
    }

    public String getQualification(){
    return this.qualification;
    }

    public void setAddress(String address){
    this.address = address;
    }

    public String getAddress(){
    return this.address;
    }

    public void setEnable(Boolean enable){
    this.enable = enable;
    }

    public Boolean getEnable(){
    return this.enable;
    }

    public void setVersion(Integer  version){
    this.version = version;
    }

    public Integer  getVersion(){
    return this.version;
    }

    public void setRemark(String remark){
    this.remark = remark;
    }

    public String getRemark(){
    return this.remark;
    }

    public void setDr(Boolean dr){
    this.dr = dr;
    }

    public Boolean getDr(){
    return this.dr;
    }

    public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
    }

    public String getCreatedBy(){
    return this.createdBy;
    }

    public void setCreatedTime(Date createdTime){
    this.createdTime = createdTime;
    }

    public Date getCreatedTime(){
    return this.createdTime;
    }

    public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
    }

    public String getUpdatedBy(){
    return this.updatedBy;
    }

    public void setUpdatedTime(Date updatedTime){
    this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime(){
    return this.updatedTime;
    }

    public void setAppId(Integer  appId){
    this.appId = appId;
    }

    public Integer  getAppId(){
    return this.appId;
    }

    public void setTenantId(String tenantId){
    this.tenantId = tenantId;
    }

    public String getTenantId(){
    return this.tenantId;
    }


}

