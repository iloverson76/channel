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
import com.deepexi.channel.service.AreaTypeService;
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
    public Long saveAreaType(AreaTypeDTO dto) {

        log.info("保存区域分类");
        if(null==dto){
            return 0L;
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

            newNode.setPath("/"+String.valueOf(newId));//首次创建

        } else {

            String parent_path=areaTypeDAO.getById(parentId).getPath()+"/"+newId;

            newNode.setPath(parent_path);
        }

        //链路
        long id=newNode.getId();

        newNode.setLinkId(id);

        areaTypeDAO.updateById(newNode);

        return id;
    }

    @Transactional
    @Override
    public boolean updateAreaTypeById(AreaTypeDTO dto) {//新的dto的数据

        UpdateWrapper<AreaTypeDO> wrapper = new UpdateWrapper<>();

        wrapper.eq("id", dto.getId());

        setCommonColumns(dto);

        AreaTypeDO ado=dto.clone(AreaTypeDO.class,CloneDirection.FORWARD);

       return areaTypeDAO.update(ado,wrapper);
    }

    private boolean deleteAreaTypeById(Long id){

        List<AreaTypeDTO> dtoList1 = listAreaTypePage(new AreaTypeQuery());

        List<AreaTypeDTO> dtoList2=ObjectCloneUtils.convertList(dtoList1,AreaTypeDTO.class,CloneDirection.FORWARD);

        for (AreaTypeDTO dto1:dtoList1){

            for (AreaTypeDTO dto2:dtoList2){

                if(dto1.getId().equals(dto2.getParentId())){//有下级不能删除
                    throw new ApplicationException("已被下级["+dto2.getAreaTypeName()+"]关联,不能删除!");
                }
            }
        }
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public boolean deleteAreaTypeByIds(List<Long> idList) {

        log.info("批量删除区域类型");

        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }
        if(idList.contains(0)){

            //parent=0不能作为id=0来删除
            idList.remove(0);
        }

        idList.forEach(id->{
            deleteAreaTypeById(id);
        });

        return Boolean.TRUE;
    }

    @Override
    public AreaTypeDTO getAreaTypeById(Long id) {

        AreaTypeDO ado=areaTypeDAO.getOne(getQueryWrapper(id));

        if (null==ado) {
            return new AreaTypeDTO();
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
       // List<AreaTypeDO> doList=areaTypeDAO.listNotLimitedNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        List<AreaTypeDTO> dtoList=listAreaTypePage(new AreaTypeQuery());

        //首次创建处理空值
        if(CollectionUtils.isEmpty(dtoList)){

            return Collections.emptyList();
        }

        return dtoList;

    }

    @Override
    public List<AreaTypeDTO> listParentNodesForUpdate(Long id) {

        log.info("更新区域分类接口:查询可用上级分类");

        //没有被限制分类的节点
        List<AreaTypeDO> unLimitedNodes=areaTypeDAO.listNotLimitedNode(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId());

        if(CollectionUtils.isEmpty(unLimitedNodes)){
            return Collections.emptyList();
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

    @Override
    public List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList) {

        return areaTypeDAO.listAreaTypeByIds(areaTyeIdList);
    }

    @Override
    public List<AreaTypeDTO> listLinkedAreas(long pk) {

        if(pk<0){
            return Collections.emptyList();
        }

        List<AreaTypeDO> eoList=areaTypeDAO.listLinkedAreas(pk);

        if(CollectionUtils.isEmpty(eoList)){
            return Collections.emptyList();
        }

        return ObjectCloneUtils.convertList(eoList,AreaTypeDTO.class,CloneDirection.OPPOSITE);
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

    }

    private void updateUnLimitedChildNodesParentIdAndPathInfo(AreaTypeDTO dto){

        //如果所选上级原来有下级,且不被被其下级限制
        long parentId=dto.getParentId();

        List<AreaTypeDO> childNodes=areaTypeDAO.listChildNodes(appRuntimeEnv.getTenantId(),appRuntimeEnv.getAppId(),parentId+"/");

        if(CollectionUtils.isNotEmpty(childNodes)){

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

    @Override
    public List<AreaTypeDTO> findByAreaIdNotInLinkIdAll(List<Long> linkIdList) {
        List<AreaTypeDO> list = areaTypeDAO.findByAreaIdNotInLinkIdAll(linkIdList);
        List<AreaTypeDTO> areaTypeDTOList = ObjectCloneUtils.convertList(list, AreaTypeDTO.class, CloneDirection.OPPOSITE);
        return areaTypeDTOList;
    }
}
