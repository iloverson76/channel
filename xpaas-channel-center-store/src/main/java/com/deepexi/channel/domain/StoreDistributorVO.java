package com.deepexi.channel.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 20:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api(value="门店关联经销商")
public class StoreDistributorVO extends AbstractObject {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    @ApiModelProperty("门店id")
    private Long storeId;;
    @ApiModelProperty("经销商id")
    private Long distributorId;
    /**
     * 经销商编码
     */
    @ApiModelProperty("经销商code")
    private String distributorCode;
    @ApiModelProperty("经销商中文名称")
    private String distributorName;
    @ApiModelProperty("上级经销商ID")
    private Long parentId;
    @ApiModelProperty("上级经销商名称")
    private String parentName;
    @ApiModelProperty("上级经销商code")
    private String parentCode;

    @ApiModelProperty("经销商等级体系id")
    private Long gradeSystemId;
    @ApiModelProperty("经销商等级体系编码")
    private String gradeSystemCode;
    @ApiModelProperty("经销商等级体系名称")
    private String gradeSystemName;
    @ApiModelProperty("经销商等级id")
    private Long gradeId;

//    @ApiModelProperty("上级经销商列表")
//    private List<StoreDistributorVO> parentDistributor;
}