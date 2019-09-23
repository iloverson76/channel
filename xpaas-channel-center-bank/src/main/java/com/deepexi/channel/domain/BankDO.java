package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
@EqualsAndHashCode(callSuper = true)
@TableName("cc_bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="BankAccount对象", description="银行账户表")
public class BankDO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    private String bankName;

}
