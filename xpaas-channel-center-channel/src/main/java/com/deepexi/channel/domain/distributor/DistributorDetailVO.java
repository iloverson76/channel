package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.BaseEntity;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.bank.BankAccountVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="经销商详细详情")
public class DistributorDetailVO extends DistributorVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("区域列表")
    private List<AreaVO> areaList;

    @ApiModelProperty("银行账号列表")
    private List<BankAccountVO> bankAccountList;
}
