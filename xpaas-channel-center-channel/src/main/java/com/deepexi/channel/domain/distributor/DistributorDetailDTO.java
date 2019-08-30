package com.deepexi.channel.domain.distributor;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@ApiModel(value="经销商详细详情")
public class DistributorDetailDTO extends DistributorDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("区域列表")
    private List<AreaDTO> areaList;

    @ApiModelProperty("银行账号列表")
    private List<BankAccountDTO> bankAccountList;
}
