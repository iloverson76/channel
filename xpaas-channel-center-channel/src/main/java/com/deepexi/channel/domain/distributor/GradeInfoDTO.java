package com.deepexi.channel.domain.distributor;

import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 经销商表
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
public class GradeInfoDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long systemId;

    private String gradeSystemName;

    private String gradeSystemCode;

    private Long gradeId;

    private String distributorGradeCode;

    private String distributorGradeName;

    private Integer limitedParent;

    private Long parentDistributorId;

    private String parentDistributorName;


}
