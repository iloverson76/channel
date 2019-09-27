package com.deepexi.channel.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

import java.util.Date;

/**
 * <p>
 * 经销商等级表
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
public class DistributorGradeDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 上级经销商等级编码
     */
    private String parentGradeCode;

    /**
     * 上级经销商等级名称
     */

    private String parentGradeName;

    /**
     * 父级分类ID
     */
    private Long parentId;

    /**
     * 是否根节点 0 是 1 否
     */
    private int root;

    /**
     * 经销商等级名称
     */
    private String distributorGradeName;

    /**
     * 经销商等级名称-英文
     */
    private String distributorGradeNameEn;

    /**
     * 经销商等级编码
     */
    private String distributorGradeCode;

    /**
     * 所属体系体系ID
     */
    private Long gradeSystemId;

    /**
     * 描述
     */
    private String description;
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
