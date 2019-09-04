package com.deepexi.channel.domain.distributor;

import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

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
public class DistributorGradeSystemDTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 等级体系名称
     */
    private String gradeSystemName;

    /**
     * 等级体系编码
     */
    private String gradeSystemCode;

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
