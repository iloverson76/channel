package com.deepexi.channel.api;

import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaQuery;
import com.deepexi.channel.domain.AreaTreeDTO;
import com.deepexi.channel.domain.AreaTypeDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-24 10:33
 */
public interface AreaApi {

    /**
     * 创建区域
     * @param dto 新增的区域实体
     * @return 新纪录ID
     */
    @PostMapping
    Long create(@RequestBody AreaDTO dto);

    /**
     * 更新区域
     * @param dto 更新的区域实体
     * @return 是否成功
     */
    @PutMapping()
    boolean update(@RequestBody AreaDTO dto);

    /**
     * 区域列表查询
     * @param query 查询条件
     * @return 区域集合
     */
    @GetMapping()
    List<AreaDTO> findPage(@ApiParam(name = "query", required = true) AreaQuery query);

    /**
     * 根据ID获取区域实体
     * @param pk 主键
     * @return 区域
     */
    @GetMapping("/{id}")
    AreaDTO getAreaById(@PathVariable(value = "id") Long pk);

    /**
     *批量删除区域实体
     * @param ids 区域ID集合
     * @return 是否成功
     */
    @DeleteMapping("/{ids}")
    boolean deleteBatch(@RequestBody List<Long> ids);

    /**
     * 单次删除区域实体
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    boolean deleteById(@PathVariable(value = "id") Long id);

    /**
     * 根据区域ID获取子区域
     * @param areaId 区域ID
     * @return 区域集合
     */
    @GetMapping("/listChildrenAreas/{areaId}")
    List<AreaDTO> listChildrenAreas(@PathVariable(value = "areaId") Long areaId);

    /**
     * 根据区域分类ID获取区域挂载的所有区域
     * @param areaTypeId 区域分类ID
     * @return 区域集合
     */
    @GetMapping("/listLinkedAreasByType/{areaTypeId}")
    List<AreaDTO> listLinkedAreasByType(@PathVariable(value = "areaTypeId") Long areaTypeId);

    /**
     * 批量更新区域实体
     * @param dtoList 区域实体列表
     * @return 是否成功
     */
    @PutMapping("/updateBatch")
    boolean updateBatch(@RequestBody List<AreaDTO> dtoList);

    /**
     * 根据ID集合获取所有区域实体
     * @param ids 区域ID集合
     * @return 区域集合
     */
    @GetMapping("/findAllByIds")
    List<AreaDTO> findAllByIds(@RequestBody List<Long> ids);

    /**
     * 查找区域区域树
     * @return 区域树集合
     */
    @GetMapping("/findTree")
    List<AreaDTO> findTree();

    /**
     * 获取所有的区域编码
     * @return 区域编码集合
     */
    @GetMapping("/listAreaCode")
    List<String> listAreaCode();

    /**
     * 获取所有的区域名称
     * @return 区域中文名集合
     */
    @GetMapping("/listAreaName")
    List<String> listAreaName();

    /**
     * 获取所有的区域英文名称
     * @return 区域英文名集合
     */
    @GetMapping("/listAreaNameEn")
    List<String> listAreaNameEn();
}
