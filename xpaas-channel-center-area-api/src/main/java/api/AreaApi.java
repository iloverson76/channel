package api;

import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaQuery;
import com.deepexi.channel.domain.AreaTreeDTO;
import com.deepexi.channel.domain.AreaTypeDTO;
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
     * @param dto 新增内容
     * @return 新增记录ID
     */
    @PostMapping
    Long create(@RequestBody AreaDTO dto);

    /**
     * 查看区域详情
     * @param id 区域ID
     * @param areaTypeId 区域所属分类ID
     * @return 区域详情实体
     */
    @GetMapping("/{id}/{areaTypeId}")
    AreaDTO detail(@RequestParam("id") Long id,@RequestParam("areaTypeId")Long areaTypeId);

    /**
     *
     * @param pk 区域ID
     * @param dto 更新内容
     * @return 是否成功
     */
    @PutMapping("/{id}")
    boolean update(@RequestParam("id") Long  pk, @RequestBody AreaDTO dto);

    /**
     *根据id批量删除区域
     * @param idList 区域ID列表
     * @param forceDelete 是否强制删除与区域关联的关系
     * @return 是否成功
     */
    @DeleteMapping("/{ids:[0-9,]+}")
    boolean deleteBatchByIds(@RequestBody List<Long> idList, @RequestParam("forceDelete") Integer forceDelete);

    /**
     * 查询区域列表-分页查询
     * @param query 查询条件
     * @return 分页列表
     */
    @GetMapping()
    List<AreaDTO> findPage(@RequestBody AreaQuery query);

    /**
     *查找所有的区域树
     * @param query 查询条件
     * @return
     */
    @GetMapping("/tree")
    List<AreaTreeDTO> buildAreaTree(@RequestBody AreaQuery query);

    /**
     * 根据区域ID查找所有下级树
     * @param areaId 区域ID
     * @return 节点树
     */
    @GetMapping("/childrenTree/{areaId}")
    List<AreaTreeDTO> listChildrenTree(@RequestParam("areaId") Long areaId);

    /**
     * 根据区域类型ID查找挂载的所有区域
     * @param areaTypeId 区域类型ID
     * @return 区域实体列表
     */
    @GetMapping("/linkedAreas/{areaTypeId}")
    List<AreaDTO> listLinkedAreasByType(@RequestParam("areaTypeId") Long areaTypeId);

    /**
     * 根据区域ID查找其上级分类
     * @param areaId 区域ID
     * @return 区域分类列表
     */
    @GetMapping("/parentAreaType/{areaId}")
    List<AreaTypeDTO> findParentAreaTypeByAreaId(@RequestParam("areaId") Long areaId);

    /**
     * 添加树节点
     * @param dto 添加的区域节点
     * @return 是否成功
     */
    @PostMapping("/tree/addNode/{id}")
    Boolean treeAddNode(@RequestBody AreaDTO dto);

    /**
     * 修改树节点
     * @param dto 更新的区域节点
     * @return 是否成功
     */
    @PutMapping("/tree/updateNode/{id}")
    boolean treeUpdateNode(@RequestBody AreaDTO dto);

    /**
     * 删除树节点
     * @param pk 区域ID
     * @return 是否成功
     */
    @DeleteMapping("/tree/deleteNode/{id}")
    boolean treeDeleteNode(@RequestParam("id") Long pk);
}
