package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.deepexi.channel.dao.AreaTypeDAO;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.IAreaTypeService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 区域类型-服务实现类
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@Service
public class AreaTypeServiceImpl implements IAreaTypeService {

    @Autowired
    AreaTypeDAO iAreaTypeDAO;

    AppRuntimeEnv appRuntimeEnv= AppRuntimeEnv.getInstance();

    @Transactional
    @Override
    public boolean saveAreaType(AreaTypeDTO dto) {

        dto.setTenantId(appRuntimeEnv.getTenantId());

        dto.setAppId(appRuntimeEnv.getAppId());

        dto.setCreatedTime(new Date());

        dto.setUpdatedTime(new Date());

        return iAreaTypeDAO.save(dto.clone(AreaTypeDO.class,CloneDirection.FORWARD));
    }

    @Override
    public boolean updateAreaTypeById(AreaTypeDTO dto) {

        UpdateWrapper<AreaTypeDO> wrapper = new UpdateWrapper<>();

        wrapper.eq("id", dto.getId());

       return iAreaTypeDAO.update(dto.clone(AreaTypeDO.class,CloneDirection.FORWARD),wrapper);
    }

    @Override
    public boolean deleteAreaTypeByIds(Set<Long> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            throw new ApplicationException(ResultEnum.IDS_NOT_EMPTY);
        }

        iAreaTypeDAO.removeAreaTypeByIds(ids);

        return Boolean.TRUE;
    }

    @Override
    public AreaTypeDTO getAreaTypeById(Long id) {

        AreaTypeDO ado=iAreaTypeDAO.getOne(getQueryWrapper(id));

        if (null==ado) {
            throw new ApplicationException(ResultEnum.AREA_TYPE_NULL);
        }

        return ado.clone(AreaTypeDTO.class,CloneDirection.OPPOSITE);

    }

    @Override
    public List<AreaTypeDTO> listAreaTypePage(AreaTypeQuery query) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<AreaTypeDO> typeList = iAreaTypeDAO.listAreaType(query);

        return ObjectCloneUtils.convertList(typeList, AreaTypeDTO.class, CloneDirection.OPPOSITE);
    }

    private QueryWrapper getQueryWrapper(Long id){

        QueryWrapper<AreaTypeDO> wp=new QueryWrapper<>();

        wp.eq("id",id);

        wp.eq("tenant_id",appRuntimeEnv.getTenantId());

        wp.eq("app_id",appRuntimeEnv.getAppId());

        return wp;
    }

}
