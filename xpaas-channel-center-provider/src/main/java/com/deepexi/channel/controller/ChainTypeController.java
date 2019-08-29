package com.deepexi.channel.controller;

import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.domain.chain.ChainTypeVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.channel.domain.eo.CcChainType;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;


@Api(value = "/连锁管理", description = "连锁管理页面")
@RestController
@RequestMapping("/api/v1/chainType")
public class ChainTypeController {

    @Autowired
    private ChainTypeService chainTypeService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求,page传-1时返回全部连锁类型")
    public  Payload findPage(@ApiParam(name = "query", required = true) ChainTypeQuery query) {
        List<ChainTypeDTO> chainTypeDTOList = chainTypeService.findPage(query);
        List<ChainTypeVO> result = ObjectCloneUtils.convertList(chainTypeDTOList, ChainTypeVO.class);
        return new Payload<>(new PageBean<>(result));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Long id) {
        ChainTypeDTO chainTypeDTO = chainTypeService.detail(id);
        if(chainTypeDTO == null){
            return new Payload<>(null);
        }
        ChainTypeVO chainTypeVO = chainTypeDTO.clone(ChainTypeVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(chainTypeVO);
    }

    @PutMapping("/{id}")
    @Transactional
    @ApiOperation(value = "根据id修改", notes = "根据id修改连锁类型")
    public Payload update(@PathVariable(value = "id", required = true) Long id, @RequestBody ChainTypeVO vo) {
        ChainTypeDTO dto = vo.clone(ChainTypeDTO.class, CloneDirection.FORWARD);
        return new Payload(chainTypeService.update(id, dto));
    }

    @PostMapping
    @ApiOperation(value = "创建连锁类型", notes = "创建连锁类型")
    public Payload create(@RequestBody  ChainTypeVO vo) {
        ChainTypeDTO dto = vo.clone(ChainTypeDTO.class, CloneDirection.FORWARD);
        return new Payload(chainTypeService.create(dto));
    }

    @DeleteMapping("/{ids}")
    @Transactional
    @ApiOperation(value = "根据id批量删除CcChainType", notes = "根据id删除CcChainType")
    public Payload delete(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload(chainTypeService.delete(ids));
    }


}