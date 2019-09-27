package com.deepexi.channel.api;

import com.deepexi.channel.domain.DistributorGradeDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorGradeBusinessApi {

    /**
     * 获取经销商等级详情
     * @param gradeId 等级ID
     * @return
     */
    @GetMapping("/gradeBusi/detail/{gradeId}")
    DistributorGradeDTO detail(@PathVariable(value = "gradeId") Long gradeId);

    /**
     * 等级分页查询
     * @param query 查询条件
     * @return 等级集合
     */
    @GetMapping("/gradeBusi/page")
    List<DistributorGradeDTO> findPage(@RequestBody DistributorGradeQuery query);

    /**
     * 更新等级时查询可用的上级
     * @param systemId 体系ID
     * @param gradeId 等级ID
     * @return 等级集合
     */
    @GetMapping("/gradeBusi/findParentNodesForUpdate/{systemId}/{gradeId}")
    List<DistributorGradeDTO> findParentNodesForUpdate(@PathVariable(value = "systemId") Long systemId,
                                                       @PathVariable(value = "gradeId")Long gradeId);

    /**
     * 新增等级时查询可用的上级
     * @param systemId 体系ID
     * @return 等级集合
     */
    @GetMapping("/gradeBusi/findParentNodesForCreate/{systemId}")
    List<DistributorGradeDTO> findParentNodesForCreate(@PathVariable(value = "systemId") Long systemId);

    /**
     * 根据体系查询所有的等级
     * @param systemId 体系ID
     * @return 等级集合
     */
    @GetMapping("/gradeBusi/findAllGradesBySystem/{systemId}")
    List<DistributorGradeDTO> findAllGradesBySystem(@PathVariable(value = "systemId") long systemId);

    /**
     * 批量删除等级
     * @param idList 等级ID集合
     * @param forceDelete 是否强制删除 0否 1是
     * @return
     */
    @DeleteMapping("/gradeBusi/deleteBatch/{forceDelete}")
    Boolean deleteBatchByIds(@RequestBody List<Long> idList,
                             @PathVariable(value = "forceDelete") Integer forceDelete);

    /**
     * 校验等级是否有经销商挂载
     * @param gradeIdList 等级ID集合
     */
    @GetMapping("/gradeBusi/validateHasDistributors")
    void validateHasDistributors(@RequestBody List<Long> gradeIdList);

    /**
     * 校验等级是否有下级
     * @param gradeIdList 等级ID集合
     */
    @GetMapping("/gradeBusi/validateHasChildren")
    void validateHasChildren(@RequestBody List<Long> gradeIdList);

    /**
     * 删除与经销商的关联关系
     * @param gradeIdList 等级ID集合
     * @return 是否成功
     */
    @GetMapping("/gradeBusi/deleteDistributors")
    Boolean deleteDistributors(@RequestBody List<Long> gradeIdList);

    /**
     * 创建等级
     * @param dto 新增实体
     * @return 新增记录的ID
     */
    @PostMapping("/gradeBusi")
    Long create(@RequestBody DistributorGradeDTO dto);

    /**
     * 更新等级
     * @param dto 更新实体
     * @return 是否成功
     */
    @PutMapping("/gradeBusi")
    Boolean update(@RequestBody DistributorGradeDTO dto);

    /**
     * 获取所有下级
     * @param id 等级ID
     * @return 等级集合
     */
    @GetMapping("/gradeBusi/listChildrenNodes/{id}")
    List<DistributorGradeDTO> listChildrenNodes(@PathVariable(value = "id") Long id);

    /**
     * 校验等级编码是否重复
     * @param gradeCode 新增的编码
     * @param systemId 体系ID
     */
    @GetMapping("/gradeBusi/validateDistributorGradeCode/{gradeCode}/{systemId}")
    void validateDistributorGradeCode(@PathVariable(value = "gradeCode") String gradeCode,
                                      @PathVariable(value = "systemId") Long systemId);

    /**
     *校验中文名称是否重复
     * @param gradeName 新增的等级中文名称
     * @param systemId 体系ID
     */
    @GetMapping("/gradeBusi/validateDistributorGradeName/{gradeName}/{systemId}")
    void validateDistributorGradeName(@PathVariable(value = "gradeName") String gradeName,
                                      @PathVariable(value = "systemId") Long systemId);

    /**
     * 校验英文名称是否重复
     * @param gradeNameEn 新增的英文名称
     * @param systemId 体系ID
     */
    @GetMapping("/gradeBusi/validateDistributorGradeNameEn/{gradeNameEn}/{systemId}")
    void validateDistributorGradeNameEn(@PathVariable(value = "gradeNameEn") String gradeNameEn,
                                        @PathVariable(value = "systemId") Long systemId);
}