package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.Pageable;
import com.deepexi.util.pojo.AbstractObject;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistributorGradeSystemQuery extends AbstractObject implements Pageable {

    private static final long serialVersionUID = 1L;

    /**
     * 等级体系名称
     */
    @ApiModelProperty("等级体系名称")
    private String gradeSystemName;

    /**
     * 等级体系编码
     */
    @ApiModelProperty("等级体系编码")
    private String gradeSystemCode;

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
