package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.deepexi.channel.dao.AreaTypeDAO;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.enums.LimitedEnum;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.business.AreaTypeService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 区域类型-服务实现类
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@Service
@Slf4j
public class AreaTypeServiceImpl implements AreaTypeService {

    @Autowired
    AreaTypeDAO areaTypeDAO;

    AppRuntimeEnv appRuntimeEnv= AppRuntimeEnv.getInstance();

    @Transactional
    @Override
    public boolean
    saveAreaType(AreaTypeDTO dto) {

        log.info("保存区域分类");
        if(null==dto){
            return false;
        }

        //编码不能重复
        ValidateAareaTypeCode(dto.getAreaTypeCode());

        //如果所选上级原来有下级,且不被被其下级限制
        updateUnLimitedChildNodesParentIdAndPathInfo(dto);

        //插入新节点
        setCommonColumns(dto);

        AreaTypeDO newNode=dto.clone(AreaTypeDO.class,CloneDirection.FORWARD);

        //save后,ado是插入数据库后返回的新数据,包括id,ado克隆要拆成两步写
        areaTypeDAO.save(newNode);

        //设置处理(id路径)
        long newId=newNode.getId();

        long parentId=newNode.getParentId();

        if (0==parentId) {

            newNode.setPath(String.valueOf(newId));//首次创建

        } else {

            String parent_path=areaTypeDAO.getById(parentId).getPath()+"/"+newId;

            newNode.setPath(parent_path);
        }

        return areaTypeDAO.updateById(newNode);
    }

    @Transactional
    @Override
    public boolean updateAreaTypeById(AreaTypeDTO dto) {//新的dto的数据--

        UpdateWrapper<AreaTypeDO> wrapper = new UpdateWrapper<>();

        wrapper.eq("id", dto.getId());

        setCommonColumns(dto);

        AreaTypeDO ado=dto.clone(AreaTypeDO.class,CloneDirection.FORWARD);

       return areaTypeDAO.update(ado,wrapper);
    }

    @Transactional
    @Override
    public boolean deleteAreaTypeByIds(Set<Long> ids) {

        log.info("批量删除区域类型");

        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }

        if(ids.contains(0)){

            ids.remove(0);//parent=0不能作为id=0来删除
        }

        return areaTypeDAO.removeByIds(ids);
    }

    @Override
    public AreaTypeDTO getAreaTypeById(Long id) {

        AreaTypeDO ado=areaTypeDAO.getOne(getQueryWrapper(id));

        if (null==ado) {
            return null;
        }

        AreaTypeDTO node = ado.clone(AreaTypeDTO.class,CloneDirection.OPPOSITE);

        long parentId=node.getParentId();

        if(0!=parentId){

            AreaTypeDO parent=areaTypeDAO.getOne(getQueryWrapper(parentId));

            node.setParentName(parent.getAreaTypeName());

            node.setParentNameEn(parent.getAreaTypeNameEn());
        }

        return node;
    }

    @Override
    public List<AreaTypeDTO> listAreaTypePage(AreaTypeQuery query) {

        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }

        List<AreaTypeDO> typeList = areaTypeDAO.listAreaTypePage(query);

        return ObjectCloneUtils.convertList(typeList, AreaTypeDTO.class, CloneDirection.OPPOSITE);
    }

    /**
     * 新增节点时的上级列表
     *
     * 上级与下级只能1:1
     *
     */
    @Override
    public List<AreaTypeDTO> listParentNodesForCreate() {

        log.info("创建区域分类接口:查询可用上级分类");

        //没有被限制分类的节点
        List<AreaTypeDO> doList=areaTypeDAO.listNotLimitedNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        //首次创建处理空值
        if(CollectionUtils.isNotEmpty(doList)){

            List<AreaTypeDTO> dtoList=ObjectCloneUtils.convertList(doList, AreaTypeDTO.class, CloneDirection.OPPOSITE);

            return dtoList;
        }
        return null;
    }

    @Override
    public List<AreaTypeDTO> listParentNodesForUpdate(Long id) {

        log.info("更新区域分类接口:查询可用上级分类");

        //没有被限制分类的节点
        List<AreaTypeDO> unLimitedNodes=areaTypeDAO.listNotLimitedNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        if(CollectionUtils.isEmpty(unLimitedNodes)){
            return null;
        }
        //如果自己原来的上级被限制了,也要选上
        AreaTypeDTO node=getAreaTypeById(id);

        long parentId=node.getParentId();

        boolean limited=LimitedEnum.LIMITED.getCode().equals(node.getLimitParent());

        if(0!=parentId&&limited){

            AreaTypeDTO parentDTO=getAreaTypeById(parentId);

            unLimitedNodes.add(parentDTO.clone(AreaTypeDO.class,CloneDirection.FORWARD));
        }

        //不能选自己和自己的所有子节点
        AreaTypeDO self=areaTypeDAO.getById(id);

        List<AreaTypeDO>  childNodes=areaTypeDAO.listChildNodes(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId(),id+"/");

        List<AreaTypeDO> removeList=new ArrayList<>();

        unLimitedNodes.forEach(u->{

            if(u.getId().equals(self.getId())){

                removeList.add(u);
            }

            if(CollectionUtils.isNotEmpty(childNodes)){

                childNodes.forEach(c->{

                    if(c.getId().equals(u.getId())){

                        removeList.add(u);
                    }
                });
            }
        });

        unLimitedNodes.removeAll(removeList);

        return ObjectCloneUtils.convertList(unLimitedNodes,AreaTypeDTO.class);
    }

    private AreaTypeDTO getParentNodeById(Long parentId){

        QueryWrapper<AreaTypeDO> wp=getQueryWrapper(parentId);

        AreaTypeDO ado=areaTypeDAO.getOne(wp);

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

        List<String> doList=areaTypeDAO.listAreaTypeCode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        if(CollectionUtils.isNotEmpty(doList)){
            doList.forEach(typeCode->{
                if(areaTypeCode.equals(typeCode)){
                    throw new ApplicationException(ResultEnum.AREA_TYPE_DUPLICATED);
                }
            });
        }
    }

    public void setCommonColumns(AreaTypeDTO dto){

        dto.setTenantId(appRuntimeEnv.getTenantId());

        dto.setAppId(appRuntimeEnv.getAppId());

        dto.setCreatedTime(new Date());

        dto.setUpdatedTime(new Date());

    }

    private void updateUnLimitedChildNodesParentIdAndPathInfo(AreaTypeDTO dto){

        //如果所选上级原来有下级,且不被被其下级限制
        long parentId=dto.getParentId();

        List<AreaTypeDO> childNodes=areaTypeDAO.listChildNodes(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId(),parentId+"/");

        if(null!=childNodes){

            childNodes.forEach(ado->{

                long childNodeParentId=ado.getParentId();

                if(parentId==childNodeParentId&&(LimitedEnum.UNLIMITED.getCode().equals(ado.getLimitParent()))){
                    //断开原来直接下级关系,使其成为自由节点
                    ado.setParentId((long)0);
                    areaTypeDAO.updateById(ado);
                }

                //删掉原来子节点的父节点路径
                ado.setPath(ado.getPath().replaceAll(parentId+"/",""));

            });
            areaTypeDAO.updateBatchById(childNodes);
        }
    }

}
