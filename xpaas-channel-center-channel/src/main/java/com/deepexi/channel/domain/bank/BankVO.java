package com.deepexi.channel.domain.bank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("银行")
public class BankVO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("银行名称")
    private String bankName;

}
