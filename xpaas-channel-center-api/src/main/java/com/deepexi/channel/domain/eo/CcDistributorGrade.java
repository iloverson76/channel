package com.deepexi.channel.domain.eo;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Collection;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
* @desc cc_distributor_grade
* @author admin
*/
//@ApiModel(description = "经销商等级表")
public class CcDistributorGrade{

// @ApiModelProperty(value = "ID")
    @TableId
    @TableField(value = "`id`")
    private Integer  id;
// @ApiModelProperty(value = "父级分类ID")
    @TableField(value = "`parent_id`")
    private Integer  parentId;
// @ApiModelProperty(value = "是否根节点 0 是 1 否")
    @TableField(value = "`root`")
    private Boolean root;
// @ApiModelProperty(value = "经销商等级名称")
    @TableField(value = "`distributor_grade_name`")
    private String distributorGradeName;
// @ApiModelProperty(value = "经销商等级编码")
    @TableField(value = "`distributor_grade_code`")
    private String distributorGradeCode;
// @ApiModelProperty(value = "等级体系ID")
    @TableField(value = "`grade_system_id`")
    private Integer  gradeSystemId;
// @ApiModelProperty(value = "版本号，乐观锁")
    @TableField(value = "`version`")
    private Integer  version;
// @ApiModelProperty(value = "备注")
    @TableField(value = "`remark`")
    private String remark;
// @ApiModelProperty(value = "描述")
    @TableField(value = "`description`")
    private String description;
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

    public void setParentId(Integer  parentId){
    this.parentId = parentId;
    }

    public Integer  getParentId(){
    return this.parentId;
    }

    public void setRoot(Boolean root){
    this.root = root;
    }

    public Boolean getRoot(){
    return this.root;
    }

    public void setDistributorGradeName(String distributorGradeName){
    this.distributorGradeName = distributorGradeName;
    }

    public String getDistributorGradeName(){
    return this.distributorGradeName;
    }

    public void setDistributorGradeCode(String distributorGradeCode){
    this.distributorGradeCode = distributorGradeCode;
    }

    public String getDistributorGradeCode(){
    return this.distributorGradeCode;
    }

    public void setGradeSystemId(Integer  gradeSystemId){
    this.gradeSystemId = gradeSystemId;
    }

    public Integer  getGradeSystemId(){
    return this.gradeSystemId;
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

    public void setDescription(String description){
    this.description = description;
    }

    public String getDescription(){
    return this.description;
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

