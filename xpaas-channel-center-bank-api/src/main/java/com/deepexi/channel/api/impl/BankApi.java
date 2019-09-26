package com.deepexi.channel.api.impl;

import com.deepexi.channel.domain.BankDTO;
import com.deepexi.channel.domain.BankQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 14:13
 */
@RequestMapping("/channel/bank")
public interface BankApi {

    /**
     * 分页查询银行
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping
    List<BankDTO> listBank(BankQuery query);

    /**
     * 分页查询银行
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping("/page")
    PageBean<BankDTO> listBankPage(BankQuery query);

    /**
     * 查询该应用下所有银行，如果库中未有数据，则初始化
     *
     * @return
     */
    @GetMapping("/listBankAndInit")
    List<BankDTO> listBank();

    /**
     * 根据id获取银行
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    BankDTO detail(@PathVariable(value = "id") Long id);

    /**
     * 创建银行
     *
     * @param bankDTO 银行dto
     * @return
     */
    @PostMapping
    Boolean create(@RequestBody BankDTO bankDTO);

    /**
     * 批量创建银行
     *
     * @param bankDTOS 银行列表
     * @return
     */
    @PostMapping("/createBatch")
    Boolean createBatch(@RequestBody List<BankDTO> bankDTOS);

    /**
     * 根据id列表批量删除银行
     *
     * @param ids id列表
     * @return
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 更新银行
     *
     * @param id
     * @param bankDTO 银行dto
     * @return
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id, @RequestBody BankDTO bankDTO);

    /**
     * 批量更新银行
     *
     * @param bankDTOS 银行dto列表
     * @return
     */
    @PutMapping("/updateBatch")
    Boolean updateBatch(@RequestBody List<BankDTO> bankDTOS);
}