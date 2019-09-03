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
* @desc cc_store
* @author admin
*/
//@ApiModel(description = "门店信息表")
public class CcStore{

// @ApiModelProperty(value = "ID")
    @TableId
    @TableField(value = "`id`")
    private Integer  id;
// @ApiModelProperty(value = "门店名称")
    @TableField(value = "`store_name`")
    private String storeName;
// @ApiModelProperty(value = "门店编码")
    @TableField(value = "`store_code`")
    private String storeCode;
// @ApiModelProperty(value = "门店英文名称")
    @TableField(value = "`store_name_en`")
    private String storeNameEn;
// @ApiModelProperty(value = "客户名称")
    @TableField(value = "`client_name`")
    private String clientName;
// @ApiModelProperty(value = "客户编码")
    @TableField(value = "`client_code`")
    private String clientCode;
// @ApiModelProperty(value = "门店地址")
    @TableField(value = "`store_address`")
    private String storeAddress;
// @ApiModelProperty(value = "门店图片")
    @TableField(value = "`store_phone`")
    private String storePhone;
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

    public void setStoreName(String storeName){
    this.storeName = storeName;
    }

    public String getStoreName(){
    return this.storeName;
    }

    public void setStoreCode(String storeCode){
    this.storeCode = storeCode;
    }

    public String getStoreCode(){
    return this.storeCode;
    }

    public void setStoreNameEn(String storeNameEn){
    this.storeNameEn = storeNameEn;
    }

    public String getStoreNameEn(){
    return this.storeNameEn;
    }

    public void setClientName(String clientName){
    this.clientName = clientName;
    }

    public String getClientName(){
    return this.clientName;
    }

    public void setClientCode(String clientCode){
    this.clientCode = clientCode;
    }

    public String getClientCode(){
    return this.clientCode;
    }

    public void setStoreAddress(String storeAddress){
    this.storeAddress = storeAddress;
    }

    public String getStoreAddress(){
    return this.storeAddress;
    }

    public void setStorePhone(String storePhone){
    this.storePhone = storePhone;
    }

    public String getStorePhone(){
    return this.storePhone;
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

