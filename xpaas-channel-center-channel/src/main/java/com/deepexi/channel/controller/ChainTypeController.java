package com.deepexi.channel.controller;

import com.deepexi.channel.businness.ChainTypeBusinessService;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.domain.chain.ChainTypeVO;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long id, @RequestBody ChainTypeVO vo) {
        vo.setId(id);
        ChainTypeDTO dto = vo.clone(ChainTypeDTO.class, CloneDirection.FORWARD);
        //判断编码是否重复
        if (!chainTypeService.isCodeUnique(dto)) {
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        //TODO 判断父级节点是否合法,是否出现环形结构

        return new Payload(chainTypeService.update(dto));
    }

    @PostMapping
    @ApiOperation(value = "创建连锁类型", notes = "创建连锁类型，创建成功返回id")
    public Payload<Long> create(@RequestBody  ChainTypeVO vo) {
        ChainTypeDTO dto = vo.clone(ChainTypeDTO.class, CloneDirection.FORWARD);
        //新增校验,编码不能重复
        if (!chainTypeService.isCodeUnique(dto)) {
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        return new Payload( chainTypeService.create(dto));
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
            throw new ApplicationException(ResultEnum.HAVE_RELATION);
        }

        return new Payload(chainTypeBusinessService.deleteChainType(ids));
    }

}