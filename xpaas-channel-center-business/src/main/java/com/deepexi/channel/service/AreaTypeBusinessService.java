package com.deepexi.channel.service;

import com.deepexi.channel.domain.AreaBusiDTO;
import com.deepexi.channel.domain.AreaTypeBusiDTO;
import com.deepexi.channel.domain.AreaTypeQuery;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface AreaTypeBusinessService {

    /**
     * 获取所有挂载的区域
      * @param pk 区域分类ID
     * @return 区域分类集合
     */
    List<AreaBusiDTO> listLinkedAreas(Long pk);

    /**
     * 区域分类分页查询
     * @param query 查询条件
     * @return 区域分类集合
     */
    List<AreaTypeBusiDTO> findPage(AreaTypeQuery query);

    /**
     * 获取分类链路集合
     * @param ids 分类ID集合
     * @return 分类集合
     */
    List<AreaTypeBusiDTO> getListAreaType(List<Long> ids);

    /**
     * 获取所有上级分类
     * @param areaId 区域ID
     * @return 区域分类集合
     */
    List<AreaTypeBusiDTO> findParentAreaTypeByAreaId(Long areaId);

    /**
     *批量删除区域分类
     * @param idList 分类ID集合
     * @param forceDelete 是否强制删除 0 否 1 是
     * @return 是否成功
     */
    boolean deleteAreaTypeByIds(List<Long> idList,Integer forceDelete);

    /**
     * 更新区域分类
     * @param dto 更新实体
     * @return 是否成功
     */
    boolean update(AreaTypeBusiDTO dto);

    /**
     * 更新分类时获取可用的上级
     * @param id 分类ID
     * @return 分类集合
     */
    List<AreaTypeBusiDTO> listParentNodesForUpdate(Long id);

    /**
     * 新建分类时获取可用的上级
     * @return 分类集合
     */
    List<AreaTypeBusiDTO> listParentNodesForCreate();

    /**
     * 删除分类
     * @param id 分类ID
     * @return 是否成功
     */
    boolean deleteAreaTypeById(Long id);

    /**
     *校验分类是否有区域挂载
     * @param idList 分类ID集合
     */
    void validateHasAreas(List<Long> idList);

    /**
     * 校验分类是否下级
     * @param idList 分类ID集合
     */
    void validateHasChildren(List<Long> idList);

    /**
     * 创建分类
     * @param dto 新增实体
     * @return 新增记录ID
     */
    Long createAreaType(AreaTypeBusiDTO dto);

    /**
     * 校验分类编码是否重复
     * @param areaTypeCode 新增的分类编码
     */
    void ValidateAareaTypeCode(String areaTypeCode);

    /**
     * 校验分类中文名是否重复
     * @param areaTypeName 新增的分类中文名
     */
    void validateAreaTypeName(String areaTypeName);

    /**
     * 校验分类英文名是否重复
     * @param areaTypeNameEn 新增的分类英文名
     */
    void validateAreaTypeNameEn(String areaTypeNameEn);

    /**
     * 获取区域分类详情
     * @param id 分类ID
     * @return
     */
    AreaTypeBusiDTO detail(Long id);
}