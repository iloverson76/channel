package com.deepexi.channel.service;

import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;

import java.util.List;
import java.util.Set;

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

    void ValidateAareaTypeCode(String areaTypeCode);
}
