package com.deepexi.channel.domain.distributor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 等级体系表
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("cc_distributor_grade_system")
public class DistributorGradeSystemDO extends BaseEntity {

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
