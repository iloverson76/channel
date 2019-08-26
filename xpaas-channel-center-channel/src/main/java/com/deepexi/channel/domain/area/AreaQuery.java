package com.deepexi.channel.domain.area;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.Pageable;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * <p>
 * 区域查询
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("区域查询")
public class AreaQuery extends AbstractObject implements Pageable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("主键")
    private Long id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域编码
     */
    private String areaCode;

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
