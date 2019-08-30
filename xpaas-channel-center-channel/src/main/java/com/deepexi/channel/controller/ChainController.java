package com.deepexi.channel.controller;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.chain.ChainVO;
import com.deepexi.channel.businness.ChainBusinessService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.ChainService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;


@Api(value = "/连锁管理", description = "连锁管理")
@RestController
@RequestMapping("/api/v1/chain")
public class ChainController {

    @Autowired
    private ChainService chainService;
    @Autowired
    private ChainBusinessService chainBusinessService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(@ApiParam(name = "query", required = true) ChainQuery query) {
        return new Payload(chainService.findPage(query));
    }

//    @GetMapping("/list")
//    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
//    public Payload findAll(CcChain eo) {
//        return new Payload(chainService.findAll(eo));
//    }
//
    @GetMapping("/{id}")
    @ApiOperation("根据id获取连锁店详情")
    public Payload detail(@PathVariable(value = "id", required = true) Long id) {
        ChainDTO chainDTO =chainService.detail(id);
        if(chainDTO == null){
            return new Payload(null);
        }
        return new Payload(chainDTO.clone(ChainVO.class, CloneDirection.OPPOSITE));
    }


    @PutMapping("/{id}")
    @Transactional
    @ApiOperation(value = "根据id修改", notes = "根据id修改连锁")
    public Payload update(@PathVariable(value = "id", required = true) Long id, @RequestBody ChainVO vo) {
        if(vo == null){
            return new Payload(false);
        }
         vo.setId(id);
         return new Payload(chainBusinessService.updateChain(vo.clone(ChainDTO.class)));
    }

    @PostMapping
    @ApiOperation(value = "创建连锁", notes = "创建连锁,创建成功返回id")
    public Payload create(@RequestBody ChainVO chainVO) {
        if(chainVO == null){
            return new Payload(false);
        }
        return new Payload(chainBusinessService.insertChain(chainVO.clone(ChainDTO.class)));
    }
//
    @DeleteMapping("/{ids}")
    @Transactional
    @ApiOperation(value = "根据id批量删除连锁", notes = "根据id删除连锁")
    public Payload delete(@PathVariable(value = "id", required = true) List<Long> ids) {
        return new Payload(chainBusinessService.deleteChain(ids));
    }
}