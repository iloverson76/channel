package com.deepexi.channel.controller;

import com.deepexi.channel.businness.ChainTypeBusinessService;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeVO;
import com.deepexi.channel.domain.chain.*;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 连琐类型Controller
 * @author mumu
 */
@Api(value = "/连锁管理", description = "连锁管理页面")
@RestController
@RequestMapping("/api/v1/chainType")
public class ChainTypeController {

    @Autowired
    private ChainTypeService chainTypeService;
    @Autowired
    private ChainTypeBusinessService chainTypeBusinessService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求,page传-1时返回全部连锁类型")
    public  Payload<PageBean<ChainTypeVO>> findPage(@ApiParam(name = "query", required = true) ChainTypeQuery query) {
        List<ChainTypeDTO> chainTypeDTOList = chainTypeBusinessService.findPage(query);
        List<ChainTypeVO> result = ObjectCloneUtils.convertList(chainTypeDTOList, ChainTypeVO.class);
        return new Payload<>(new PageBean<>(result));
    }

    @GetMapping("/{id}")
    public Payload<ChainTypeVO> detail(@PathVariable(value = "id", required = true) Long id) {
        ChainTypeDTO chainTypeDTO = chainTypeService.detail(id);
        if(chainTypeDTO == null){
            return new Payload<>(null);
        }
        ChainTypeVO chainTypeVO = chainTypeDTO.clone(ChainTypeVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(chainTypeVO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改", notes = "根据id修改连锁类型")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long id,@Valid @RequestBody ChainTypeVO vo) {
        vo.setId(id);
        ChainTypeDTO dto = vo.clone(ChainTypeDTO.class, CloneDirection.FORWARD);
        //更新校验是否被门店关联、或在树形结构中
        if(chainTypeBusinessService.haveRelation(dto)){
            throw new ApplicationException(ResultEnum.CHAIN_TYPE_HAVE_STORE_OR_TREE_RELATION);
        }
        //判断编码是否重复
        if (!chainTypeService.isCodeUnique(dto)) {
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //判断名称是否重复
        if(!chainTypeService.isNameUnique(dto)){
            throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
        }
        //限制上级分类
        if(!chainTypeService.isParentLegal(dto)){
            throw new ApplicationException(ResultEnum.PARENT_ILLEGAL);
        }
        return new Payload(chainTypeBusinessService.update(dto));
    }

    @PostMapping
    @ApiOperation(value = "创建连锁类型", notes = "创建连锁类型，创建成功返回id")
    public Payload<Long> create(@Valid @RequestBody  ChainTypeVO vo) {
        ChainTypeDTO dto = vo.clone(ChainTypeDTO.class, CloneDirection.FORWARD);
        //新增校验,编码不能重复
        if (!chainTypeService.isCodeUnique(dto)) {
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //判断名称是否重复
        if(!chainTypeService.isNameUnique(dto)){
            throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
        }
        //限制上级分类
        if(!chainTypeService.isParentLegal(dto)){
            throw new ApplicationException(ResultEnum.PARENT_ILLEGAL);
        }
        return new Payload( chainTypeBusinessService.create(dto));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据id批量删除连锁类型", notes = "根据id批量删除连锁类型，id用逗号隔开")
    public Payload<Boolean> delete(@PathVariable(value = "id", required = true) String id) {
        List<Long> ids = Arrays.stream(id.split(",")).map(Long::parseLong).collect(Collectors.toList());
        //删除校验，是否具有儿子节点，是否被其他连锁关联
        if(chainTypeService.haveChildren(ids)){
            throw new ApplicationException(ResultEnum.HAVE_CHILDREN);
        }
        if(chainTypeBusinessService.haveChainRelation(ids)){
            throw new ApplicationException(ResultEnum.CHAIN_TYPE_HAVE_RELATION);
        }

        return new Payload(chainTypeBusinessService.deleteChainType(ids));
    }

    @GetMapping("/parentChainType/{id}")
    @ApiOperation(value = "修改分类时，根据类型的id获取合法父级类型列表",notes = "根据类型的id获取合法父级类型列表")
    public Payload<List<ChainTypeVO>> getLegalParentChainType(@PathVariable(value = "id", required = true) Long id){
        List<ChainTypeDTO> list = chainTypeBusinessService.getLegalParentChainType(id);
        if(CollectionUtil.isEmpty(list)){
            return new Payload<>(null);
        }
        return new Payload<>(ObjectCloneUtils.convertList(list, ChainTypeVO.class));
    }

    @GetMapping("/linkIdNotIn")
    @ApiOperation("查询是否已经关联")
    public Payload<ChainTypeListLinkVO> getListChainType(@RequestParam String linkIds){
        List<Long> ids = new ArrayList<>();
        if (StringUtil.isNotEmpty(linkIds)){
            ids = Arrays.stream(linkIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
        }
        ChainTypeListLinkVO vo = new ChainTypeListLinkVO();
        List<ChainTypeDTO> listChainType = chainTypeBusinessService.getListChainType(ids);
        List<ChainTypeVO> chainTypeVOList = ObjectCloneUtils.convertList(listChainType, ChainTypeVO.class, CloneDirection.OPPOSITE);
        if (CollectionUtil.isEmpty(chainTypeVOList)){
            return new Payload<>();
        }
        Set<Long> setMap = chainTypeVOList.stream().filter(s->null!=s.getLinkId()).map(ChainTypeVO::getLinkId).collect(Collectors.toSet());
        vo.setLinkType(setMap.size());
        vo.setChainType(chainTypeVOList);
        return new Payload<>(vo);
    }

    @GetMapping("/limitedCreate")
    @ApiOperation("查询未受分类限制上级-新增用")
    public Payload<PageBean<ChainTypeVO>> listParentForCreate() {
        //方法从陈鹏AreaTypeController拷贝

        List<ChainTypeDTO> dtoList = chainTypeService.listParentNodesForCreate();

        List<ChainTypeVO> voList = ObjectCloneUtils.convertList(dtoList, ChainTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }
    @GetMapping("/limitedUpdaTe/{id:[0-9,]+}")
    @ApiOperation("查询未受分类限制上级-更新用")
    public Payload<PageBean<ChainTypeVO>> listParentForUpdate(@PathVariable Long id) {
        //方法从陈鹏AreaTypeController拷贝

        List<ChainTypeDTO> dtoList = chainTypeService.listParentNodesForUpdate(id);

        List<ChainTypeVO> voList = ObjectCloneUtils.convertList(dtoList, ChainTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/tree/node/parentChainType/{id}")
    @ApiOperation(value = "树行结构中，添加或编辑树节点时，根据当前分类Id，获取合法的上级节点分类")
    public Payload<List<ChainTypeDTO>> parentChainType(@PathVariable(value = "id" , required = true)Long chainTypeId) {
        return new Payload<>(chainTypeBusinessService.parentChainType(chainTypeId));
    }
}