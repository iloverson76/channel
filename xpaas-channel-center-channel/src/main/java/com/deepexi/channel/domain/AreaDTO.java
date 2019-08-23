package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 区域分类ID
     */
    private Integer areaTypeId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 区域英文名称
     */
    private String areaNameEn;

    /**
     * 描述
     */
    private String description;

}
