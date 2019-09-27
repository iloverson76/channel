package com.deepexi.channel.api;

import com.deepexi.channel.domain.AreaTypeDTO;
import com.deepexi.channel.domain.AreaTypeQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-24 10:33
 */
public interface AreaTypeApi {

    /**
     * 创建区域分类
     * @param dto 分类实体
     * @return 新增记录ID
     */
    @PostMapping
    Long saveAreaType(@RequestBody AreaTypeDTO dto);

    /**
     * 修改区域分类
     * @param dto 分类实体
     * @return 是否成功
     */
    @PutMapping
    boolean updateAreaTypeById(@RequestBody AreaTypeDTO dto);

    /**
     * 批量更新区域分类实体
     * @param dtoList 分类实体列表
     * @return 是够成功
     */
    @PutMapping("/updateBatch")
    boolean updateAreaTypeByIds(@RequestBody List<AreaTypeDTO> dtoList);

    /**
     * 删除区域分类
     * @param id 分类ID
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    boolean deleteAreaTypeById(@PathVariable(value = "id") Long id);

    /**
     * 批量删除区域分类实体
     * @param idList 分类ID集合
     * @return 是否成功
     */
    @DeleteMapping("/deleteBatch")
    boolean deleteAreaTypeByIds(@RequestBody List<Long> idList);

    /**
     * 区域分类列表查询
     * @param query 查询条件
     * @return 区域分类集合
     */
    @GetMapping("/page")
    List<AreaTypeDTO> listAreaTypePage(@RequestBody AreaTypeQuery query);

    /**
     * 获取区域分类
     * @param id 分类ID
     * @return 区域分类
     */
    @GetMapping("/type/{id}")
    AreaTypeDTO getAreaTypeById(@PathVariable(value = "id") Long id);

    /**
     * 批量获取区域分类
     * @param areaTyeIdList 分类ID集合
     * @return 区域分类集合
     */
    @GetMapping("/listAreaTypeByIds")
    List<AreaTypeDTO> listAreaTypeByIds(@RequestBody List<Long> areaTyeIdList);

    /**
     * 获取分类下挂载的所有区域
     * @param pk 分类ID
     * @return 区域分类集合
     */
    @GetMapping("/listLinkedAreas/{id}")
    List<AreaTypeDTO> listLinkedAreas(@PathVariable(value = "id") long pk);

    /**
     * 获取不在链路上的所有分类
     * @param linkIdList 链路ID集合
     * @return 区域分类集合
     */
    @GetMapping("/findByAreaIdNotInLinkIdAll")
    List<AreaTypeDTO> findByAreaIdNotInLinkIdAll(@RequestBody List<Long> linkIdList);

    /**
     * 获取一条区域分类
     * @param id 分类ID
     * @return 区域分类
     */
    @GetMapping("/{id}")
    AreaTypeDTO getById(@PathVariable(value = "id") Long id);

    /**
     * 获取子分类
     * @param pathStr 路径
     * @return 区域分类集合
     */
    @GetMapping("/listChildNodes/{pathStr}")
    List<AreaTypeDTO> listChildNodes(@PathVariable(value = "pathStr") String pathStr);

    /**
     * 获取所有区域分类编码
     * @return 区域分类编码集合
     */
    @GetMapping("/listAreaTypeCode")
    List<String> listAreaTypeCode();

    /**
     * 获取所有区域分类中文名
     * @return 区域分类中文名集合
     */
    @GetMapping("/listAreaTypeName")
    List<String> listAreaTypeName();

    /**
     * 获取所有区域分类英文名
     * @return 区域分类英文名集合
     */
    @GetMapping("/listAreaTypeNameEn")
    List<String> listAreaTypeNameEn();
}
