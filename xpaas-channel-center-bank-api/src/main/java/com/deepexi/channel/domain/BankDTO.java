package com.deepexi.channel.domain;

import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankDTO extends SuperEntity {

    private static final long serialVersionUID = 1L;

    private String bankName;

}
