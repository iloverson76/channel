package com.deepexi.channel.domain.chain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 连锁类型表
 * </p>
 *
 * @author mumu
 * @since 2019-08-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("连锁类型")
public class ChainTypeQuery extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id数组
     */
    @ApiModelProperty("连锁类型id数组")
    private List<Long> ids;

    /**
     * 父级分类ID
     */
    @ApiModelProperty("父节点ID")
    private Long parentId;

    /**
     * 连锁分类名称
     */
    @ApiModelProperty("连锁分类名称")
    private String chainTypeName;

    /**
     * 连锁分类编码
     */
    @ApiModelProperty("连锁分类编码")
    private String chainTypeCode;


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
