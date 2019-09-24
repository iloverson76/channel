package com.deepexi.channel.service;


import com.deepexi.channel.domain.AreaTypeDTO;
import com.deepexi.channel.domain.AreaTypeQuery;

import java.util.List;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
public interface AreaTypeService {

    Long saveAreaType(AreaTypeDTO dto);

    boolean updateAreaTypeById(AreaTypeDTO dto);

    boolean updateAreaTypeByIds(List<AreaTypeDTO> dtoList);

    boolean deleteAreaTypeById(Long id);

    boolean deleteAreaTypeByIds(List<Long> idList);

    List<AreaTypeDTO> listAreaTypePage(AreaTypeQuery query);

    AreaTypeDTO getAreaTypeById(Long id);

    List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList);

    List<AreaTypeDTO> listLinkedAreas(long pk);

    List<AreaTypeDTO> findByAreaIdNotInLinkIdAll(List<Long> linkIdList);

    AreaTypeDTO getById(Long id);

    List<AreaTypeDTO> listChildNodes(String s);

    List<String> listAreaTypeCode();

    List<String> listAreaTypeName();

    List<String> listAreaTypeNameEn();
}
