package com.deepexi.channel.domain.chain;

import com.deepexi.channel.domain.bank.BankAccountDTO;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChainDetailDTO extends ChainDTO {
    /**
     * 银行账户列表
     */
    private List<BankAccountDTO> bankAccountList;

    /**
     * 父节点名称
     */
    private String parentName;

    /**
     * 连锁分类名称
     */
    private String chainTypeName;
}
