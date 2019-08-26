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
    @ApiModelProperty(value= "父节点ID",example = "123")
    private Long parentId;

    /**
     * 连锁分类ID
     */
    @ApiModelProperty(value = "连锁分类ID",example = "123")
    private Long chainTypeId;

    /**
     * 连锁名称
     */
    @ApiModelProperty(value = "连锁名称",example = "家乐福")
    private String chainName;

    /**
     * 连锁编码
     */
    @ApiModelProperty(value = "连锁编码",example = "abcd123")
    private String chainCode;


    /**
     * 页码,传-1代表不分页查询
     */
    @ApiModelProperty(value = "页码",example = "1")
    private Integer page;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量", example = "10")
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
