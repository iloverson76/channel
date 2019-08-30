package com.deepexi.channel.domain.bank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@TableName("cc_bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="BankAccount对象", description="银行账户表")
public class BankDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String bankName;

}
