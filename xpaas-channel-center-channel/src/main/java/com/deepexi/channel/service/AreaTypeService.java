package com.deepexi.channel.service;

import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
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

    boolean saveAreaType(AreaTypeDTO dto);

    boolean updateAreaTypeById(AreaTypeDTO dto);

    boolean deleteAreaTypeByIds(Set<Long> ids);

    List<AreaTypeDTO> listAreaTypePage(AreaTypeQuery query);

    AreaTypeDTO getAreaTypeById(Long id);

    List<AreaTypeDTO> listParentNodesForCreate();

    List<AreaTypeDTO> listParentNodesForUpdate(Long id);

    List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList);
}
