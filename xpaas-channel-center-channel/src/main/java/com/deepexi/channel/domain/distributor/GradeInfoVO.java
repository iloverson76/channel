package com.deepexi.channel.domain.distributor;

import com.deepexi.util.pojo.AbstractObject;
import lombok.*;

import java.util.List;

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
public class GradeInfoVO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long systemId;

    private String systemName;

    private String systemCode;

    private Long gradeId;

    private String gradeCode;

    private Integer limitedParent;

    private List<DistributorGradeSystemDTO> parentDistributors;

}
