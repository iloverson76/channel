package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.deepexi.channel.dao.IAreaTypeDAO;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.enums.LimitedEnum;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Area;
import java.util.Date;
import java.util.HashMap;
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
public class AreaTypeServiceImpl implements AreaTypeService {

    @Autowired
    IAreaTypeDAO iAreaTypeDAO;

    AppRuntimeEnv appRuntimeEnv= AppRuntimeEnv.getInstance();

    @Transactional
    @Override
    public boolean saveAreaType(AreaTypeDTO dto) {

        //第一次创建还没处理

        //编码不能重复
        ValidateAareaTypeCode(dto.getAreaTypeCode());

        setCommonColumn(dto);

        //如果新上级原来有下级,且不被下级限制
       long selfId=dto.getParentId();

       long newParentId=dto.getParentId();

       AreaTypeDTO origChildNode=getChildNodeById(newParentId);

       boolean unLimited=(LimitedEnum.UNLIMITED.getCode()==origChildNode.getLimitParent());

       if(null!=origChildNode&&unLimited){

           origChildNode.setParentId((long)0);//断开原来下级关系

           iAreaTypeDAO.updateById(origChildNode.clone(AreaTypeDO.class,CloneDirection.FORWARD));
       }

        //原来的上级关系不用维护,新的信息直接覆盖
        return iAreaTypeDAO.save(dto.clone(AreaTypeDO.class,CloneDirection.FORWARD));
    }

    @Transactional
    @Override
    public boolean updateAreaTypeById(AreaTypeDTO dto) {//新的dto的数据

        UpdateWrapper<AreaTypeDO> wrapper = new UpdateWrapper<>();

        wrapper.eq("id", dto.getId());

        setCommonColumn(dto);

        AreaTypeDO ado=dto.clone(AreaTypeDO.class,CloneDirection.FORWARD);

       return iAreaTypeDAO.update(ado,wrapper);
    }

    @Transactional
    @Override
    public boolean deleteAreaTypeByIds(Set<HashMap<Long,Long>> idSet) {

        if (CollectionUtils.isEmpty(idSet)) {
            throw new ApplicationException(ResultEnum.IDS_NOT_EMPTY);
        }

        idSet.forEach(map->{

            long id=map.get("id");

            long parentId=map.get("parentId");

            //如果有上级
            AreaTypeDTO parentDTO=getParentNodeById(parentId);

            if(null!=parentDTO){

                AreaTypeDO ado=new AreaTypeDO();

                ado.setId(id);

                ado.setParentId((long)0);//断开上级关系

                iAreaTypeDAO.updateById(ado);
            }
            //如果有下级
            AreaTypeDTO childDTO=getChildNodeById(id);

            if(null!=childDTO){

                childDTO.setParentId((long)0);//断开下级关系

                iAreaTypeDAO.updateById(childDTO.clone(AreaTypeDO.class,CloneDirection.FORWARD));
            }

            iAreaTypeDAO.removeById(id);
        });

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

        List<AreaTypeDO> typeList = iAreaTypeDAO.listAreaTypePage(query);

        return ObjectCloneUtils.convertList(typeList, AreaTypeDTO.class, CloneDirection.OPPOSITE);
    }

    /**
     * 新增节点时的上级列表
     *
     * 上级与下级只能1:1
     *
     */
    @Override
    public List<AreaTypeDTO> listParentForCreate() {

        //没有被限制分类的节点
        List<AreaTypeDO> doList=iAreaTypeDAO.listNotLimitedNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        //首次创建处理空值
        if(CollectionUtils.isNotEmpty(doList)){

            List<AreaTypeDTO> dtoList=ObjectCloneUtils.convertList(doList, AreaTypeDTO.class, CloneDirection.OPPOSITE);

            return dtoList;
        }
        return null;
    }

    @Override
    public List<AreaTypeDTO> listParentForUpdate(Long id,Long parentId) {

        //没有被限制分类的节点
        List<AreaTypeDO> doList=iAreaTypeDAO.listNotLimitedNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        List<AreaTypeDTO> dtoList= ObjectCloneUtils.convertList(doList,AreaTypeDTO.class);

        //加上自己的上级
        if(0!=parentId){

            AreaTypeDTO parentDTO=getAreaTypeById(parentId);

            dtoList.add(parentDTO);
        }

        //不能选自己的下级
        AreaTypeDTO childDTO=getChildNodeById(id);

        if(null!=childDTO){

            dtoList.remove(childDTO);
        }

        //不能选自己
        doList.forEach(list->{

            if(list.getId()==id){

                doList.remove(list);
            }
        });

        return dtoList;
    }

    private AreaTypeDTO getParentNodeById(Long parentId){

        QueryWrapper<AreaTypeDO> wp=getQueryWrapper(parentId);

        AreaTypeDO ado=iAreaTypeDAO.getOne(wp);

        if(null!=ado){
            return ado.clone(AreaTypeDTO.class,CloneDirection.OPPOSITE);
        }
        return null;
    }

    private AreaTypeDTO getChildNodeById(Long id){

        AreaTypeDO ado=iAreaTypeDAO.getChildNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId(),id);

        if(null!=ado){
            return ado.clone(AreaTypeDTO.class,CloneDirection.OPPOSITE);
        }
        return null;
    }

    private QueryWrapper<AreaTypeDO> getQueryWrapper(Long id){

        QueryWrapper<AreaTypeDO> wp=new QueryWrapper<>();

        if(null!=id){
            wp.eq("id",id);
        }

        wp.eq("tenant_id",appRuntimeEnv.getTenantId());

        wp.eq("app_id",appRuntimeEnv.getAppId());

        return wp;
    }

    private void ValidateAareaTypeCode(String areaTypeCode){

        List<String> doList=iAreaTypeDAO.listAreaTypeCode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        if(CollectionUtils.isNotEmpty(doList)){
            doList.forEach(typeCode->{
                if(areaTypeCode.equals(typeCode)){
                    throw new ApplicationException(ResultEnum.AREA_TYPE_DUPLICATED);
                }
            });
        }
    }

    public void setCommonColumn(AreaTypeDTO dto){

        dto.setTenantId(appRuntimeEnv.getTenantId());

        dto.setAppId(appRuntimeEnv.getAppId());

        dto.setCreatedTime(new Date());

        dto.setUpdatedTime(new Date());

    }

}
