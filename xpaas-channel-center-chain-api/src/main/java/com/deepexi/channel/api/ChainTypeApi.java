package com.deepexi.channel.api;

import com.deepexi.channel.domain.ChainTypeDTO;
import com.deepexi.channel.domain.ChainTypeQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 19:07
 */
@RequestMapping("/channel/chainType")
public interface ChainTypeApi {

    /**
     * 分页获取连琐类型列表
     *
     * @param query 查询条件
     * @return 连琐类型列表
     */
    @GetMapping
    List<ChainTypeDTO> findList(ChainTypeQuery query);

    /**
     * 分页获取连琐类型列表
     *
     * @param query 查询条件
     * @return 连琐类型列表
     */
    @GetMapping("/page")
    PageBean<ChainTypeDTO> findListPage(ChainTypeQuery query);

    /**
     * 获取门店类型详情
     *
     * @param id 门店类型id
     * @return 门店类型详情
     */
    @GetMapping("/{id}")
    ChainTypeDTO detail(@PathVariable(value = "id") Long id);

    /**
     * 更新门店类型
     *
     * @param id  连琐类型id
     * @param dto 门店类型dto
     * @return 更新结果boolean
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id, @RequestBody ChainTypeDTO dto);

    /**
     * 批量更新ChainType
     *
     * @param list 门店类型列表
     * @return 更新结果boolean
     */
    @PutMapping("/updateBatch")
    Boolean updateBatch(@RequestBody List<ChainTypeDTO> list);

    /**
     * 创建门店类型
     *
     * @param dto 门店类型dto
     * @return 新增结果boolean
     */
    @PostMapping
    Long create(@RequestBody ChainTypeDTO dto);

    /**
     * 批量创建门店类型
     *
     * @param dtos 门店类型dto列表
     * @return 新增结果boolean
     */
    @PostMapping("/createBatch")
    Boolean createBatch(@RequestBody List<ChainTypeDTO> dtos);

    /**
     * 批量删除
     *
     * @param ids 门店类型id列表
     * @return 删除结果boolean
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 判断编码是否重复，具备排除本身功能
     *
     * @param dto 门店类型dto，若新增时调用，id传0
     * @return 编码唯一true，编码不唯一false
     */
    @GetMapping("/isCodeUnique")
    boolean isCodeUnique(ChainTypeDTO dto);

    /**
     * 判断名字是否重复，具备排除本身功能
     *
     * @param dto 门店类型dto，若新增时调用，id传0
     * @return 名字唯一true，名字不唯一false
     */
    @GetMapping("/isNameUnique")
    boolean isNameUnique(ChainTypeDTO dto);

    /**
     * 判断dto设置的上级是否合法，限制上级是否合法
     *
     * @param dto 连琐类型dto 传入id以及parentId
     * @return 合法true，非法false
     */
    @GetMapping("/isParentLegal")
    boolean isParentLegal(ChainTypeDTO dto);

    /**
     * 查询未受分类限制上级-新增用
     *
     * @return 合法的上级连琐分类
     */
    @GetMapping("/listParentNodesForCreate")
    List<ChainTypeDTO> listParentNodesForCreate();

    /**
     * 查询未受分类限制上级-更新用
     *
     * @param id 连琐类型id
     * @return 合法的上级连琐分类
     */
    @GetMapping("/listParentNodesForUpdate/{id}")
    List<ChainTypeDTO> listParentNodesForUpdate(@PathVariable(value = "id") Long id);

}