package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.BaseEntity;
import lombok.*;

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
public class DistributorGradeSystemVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
}
