package com.deepexi.channel.service;


import com.deepexi.channel.domain.AreaTypeDTO;
import com.deepexi.channel.domain.AreaTypeQuery;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface AreaTypeService {

    /**
     * 创建区域分类
     * @param dto 分类实体
     * @return 新增记录ID
     */
    Long saveAreaType(AreaTypeDTO dto);

    /**
     * 修改区域分类
     * @param dto 分类实体
     * @return 是否成功
     */
    boolean updateAreaTypeById(AreaTypeDTO dto);

    /**
     * 批量更新区域分类实体
     * @param dtoList 分类实体列表
     * @return 是够成功
     */
    boolean updateAreaTypeByIds(List<AreaTypeDTO> dtoList);

    /**
     * 删除区域分类
     * @param id 分类ID
     * @return 是否成功
     */
    boolean deleteAreaTypeById(Long id);

    /**
     * 批量删除区域分类实体
     * @param idList 分类ID集合
     * @return 是否成功
     */
    boolean deleteAreaTypeByIds(List<Long> idList);

    /**
     * 区域分类列表查询
     * @param query 查询条件
     * @return 区域分类集合
     */
    List<AreaTypeDTO> listAreaTypePage(AreaTypeQuery query);

    /**
     * 获取区域分类
     * @param id 分类ID
     * @return 区域分类
     */
    AreaTypeDTO getAreaTypeById(Long id);

    /**
     * 批量获取区域分类
     * @param areaTyeIdList 分类ID集合
     * @return 区域分类集合
     */
    List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList);

    /**
     * 获取分类下挂载的所有区域
     * @param pk 分类ID
     * @return 区域分类集合
     */
    List<AreaTypeDTO> listLinkedAreas(long pk);

    /**
     * 获取不在链路上的所有分类
     * @param linkIdList 链路ID集合
     * @return 区域分类集合
     */
    List<AreaTypeDTO> findByAreaIdNotInLinkIdAll(List<Long> linkIdList);

    /**
     * 获取一条区域分类
     * @param id 分类ID
     * @return 区域分类
     */
    AreaTypeDTO getById(Long id);

    /**
     * 获取子分类
     * @param pathStr 路径
     * @return 区域分类集合
     */
    List<AreaTypeDTO> listChildNodes(String pathStr);

    /**
     * 获取所有区域分类编码
     * @return 区域分类编码集合
     */
    List<String> listAreaTypeCode();

    /**
     * 获取所有区域分类中文名
     * @return 区域分类中文名集合
     */
    List<String> listAreaTypeName();

    /**
     * 获取所有区域分类英文名
     * @return 区域分类英文名集合
     */
    List<String> listAreaTypeNameEn();
}
