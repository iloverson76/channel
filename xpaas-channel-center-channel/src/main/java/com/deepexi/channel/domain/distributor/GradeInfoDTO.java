package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
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
