package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * <p>
 * 等级体系表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("供应商查询")
public abstract class CommQuery extends AbstractObject{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "123")
    private Long id;

    @ApiModelProperty(value = "租户id", example = "123456")
    private String tenantId;

    @ApiModelProperty(value = "应用id", example = "123456789")
    private String appId;

    /**
     * 開始時間
     */
    @ApiModelProperty("创建时间-开始日期")
    private Date startTime;


    /**
     * 结束时间
     */
    @ApiModelProperty("创建时间-结束日期")
    private Date endTime;

    /**
     * 更新開始時間
     */
    @ApiModelProperty("更新时间-开始日期")
    private Date updateStartTime;

    /**
     * 更新结束时间
     */
    @ApiModelProperty("更新时间-结束日期")
    private Date updateEndTime;

    @ApiModelProperty("页码")
    @Min(value = -1,message = "page最小为-1,代表不分页")
    @Builder.Default
    private Integer page = -1;

    @ApiModelProperty("每页数量")
    @Min(value = 0,message = "size最小为0")
    @Builder.Default
    private Integer size = 10;

}
