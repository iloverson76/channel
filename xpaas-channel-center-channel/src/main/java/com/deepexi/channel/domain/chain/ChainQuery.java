package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * <p>
 * 连锁表
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
@ApiModel("连锁")
public class ChainQuery extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 连锁分类ID
     */
    private Long chainTypeId;

    /**
     * 连锁名称
     */
    private String chainName;

    /**
     * 连锁编码
     */
    private String chainCode;


    /**
     * 页码,传-1代表不分页查询
     */
    @ApiModelProperty("页码")
    private Integer page;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private Integer size;

    /**
     * 開始時間
     */
    @ApiModelProperty("開始時間")
    private Date startTime;


    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 更新開始時間
     */
    @ApiModelProperty("更新開始時間")
    private Date updateStartTime;


    /**
     * 更新结束时间
     */
    @ApiModelProperty("更新结束时间")
    private Date updateEndTime;
}
