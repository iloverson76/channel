package com.deepexi.channel.domain.bank;

import com.deepexi.channel.domain.BaseEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String bankName;

}
