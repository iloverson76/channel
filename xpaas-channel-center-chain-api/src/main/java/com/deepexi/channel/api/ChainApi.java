package com.deepexi.channel.api;

import com.deepexi.channel.domain.ChainDTO;
import com.deepexi.channel.domain.ChainDetailDTO;
import com.deepexi.channel.domain.ChainQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 20:00
 */
public interface ChainApi {

    /**
     * 分页获取连琐列表
     *
     * @param query 查询条件
     * @return 连琐列表
     */
    @GetMapping
    List<ChainDTO> findList(ChainQuery query);

    /**
     * 分页获取连琐列表
     *
     * @param query 查询条件
     * @return 连琐列表
     */
    @GetMapping("/page")
    PageBean<ChainDTO> findPage(ChainQuery query);

    /**
     * 获取连琐详情
     *
     * @param id 连琐id
     * @return 连琐详情
     */
    @GetMapping("/{id}")
    ChainDTO detail(@PathVariable(value = "id") Long id);

    /**
     * 更新连琐
     *
     * @param id  连琐id
     * @param dto 连琐dto
     * @return 修改结果 Boolean
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id, @RequestBody ChainDTO dto);

    /**
     * 批量更新连琐
     *
     * @param dtos 连琐dto列表
     * @return 修改结果 Boolean
     */
    @PutMapping("/updateBatch")
    Boolean updateBatch(@RequestBody List<ChainDTO> dtos);

    /**
     * 创建连琐
     *
     * @param dto 连琐dto
     * @return 连琐id
     */
    @PostMapping
    Long create(@RequestBody ChainDTO dto);

    /**
     * 批量创建连琐
     *
     * @param dtos 连琐dto列表
     * @return 新增结果 Boolean
     */
    @PostMapping("/createBatch")
    Boolean createBatch(@RequestBody List<ChainDTO> dtos);
//

    /**
     * 批量删除
     *
     * @param ids 连琐id列表
     * @return 删除结果 Boolean
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 编码是否唯一，更新时会排除本身
     *
     * @param dto 连琐dto，对于新增的dto没有id时，id传0
     * @return true编码唯一，false编码不唯一
     */
    @GetMapping("/isCodeUnique")
    boolean isCodeUnique(ChainDTO dto);

    /**
     * 批量更新path 和parentId，用于连琐树形结构用
     *
     * @param chainDTOS 连琐dto列表
     * @return 更新结果 Boolean
     */
    @PutMapping("/updatePathAndParentIdBatch")
    boolean updatePathAndParentIdBatch(@RequestBody List<ChainDTO> chainDTOS);

    /**
     * 获取整棵树的节点，展示三层的整棵树节点
     *
     * @return 连琐列表
     */
    @GetMapping("/getChainTreeNode")
    List<ChainDTO> getChainTreeNode();

    /**
     * 重置所有path和parentId
     *
     * @return 更新结果Boolean
     */
    @PutMapping("/resetTree")
    Boolean resetTree();

    /**
     * 判断名称是否重复
     *
     * @param dto
     * @return true重复，false不重复
     */
    @GetMapping("/isNameUnique")
    boolean isNameUnique(ChainDetailDTO dto);

    /**
     * 更新连琐的path和parentId，修改或新增树节点用
     *
     * @param chainDTO
     * @return 更新结果Boolean
     */
    @PutMapping("/updatePathAndParentId")
    Boolean updatePathAndParentId(@RequestBody ChainDTO chainDTO);

}