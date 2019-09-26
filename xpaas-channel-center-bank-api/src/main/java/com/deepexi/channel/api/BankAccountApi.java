package com.deepexi.channel.api;

import com.deepexi.channel.domain.BankAccountDTO;
import com.deepexi.channel.domain.BankAccountQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 15:01
 */
@RequestMapping("/channel/bankAccount")
public interface BankAccountApi {

    /**
     * 分页查询银行账户列表
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping
    List<BankAccountDTO> findList(BankAccountQuery query);

    /**
     * 分页查询银行账户列表
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping("/page")
    PageBean<BankAccountDTO> listBankAccountPage(BankAccountQuery query);

    /**
     * 批量删除
     *
     * @param ids 银行账户id列表
     * @return
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 创建银行账户
     *
     * @param dto 银行账户dto
     * @return
     */
    @PostMapping
    Long create(@RequestBody BankAccountDTO dto);

    /**
     * 批量新增银行账户
     *
     * @param bankAccountDTOS 银行账户列表
     * @return
     */
    @PostMapping("/createBatch")
    List<BankAccountDTO> saveBatch(@RequestBody List<BankAccountDTO> bankAccountDTOS);

    /**
     * 更新银行账户
     *
     * @param id  银行账户id
     * @param dto 银行账户dto
     * @return
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id,@RequestBody BankAccountDTO dto);

    /**
     * 批量更新银行账户
     *
     * @param dtos 银行账户列表
     * @return
     */
    @PutMapping("/updateBatch")
    Boolean updateBatch(@RequestBody List<BankAccountDTO> dtos);
}