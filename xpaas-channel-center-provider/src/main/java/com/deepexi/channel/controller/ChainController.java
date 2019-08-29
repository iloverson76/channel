package com.deepexi.channel.controller;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.chain.ChainVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.ChainService;
import com.deepexi.channel.domain.eo.CcChain;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;


@Api(value = "/连锁管理", description = "连锁管理")
@RestController
@RequestMapping("/api/v1/chain")
public class ChainController {

    @Autowired
    private ChainService chainService;

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
         vo.setId(id);
         return new Payload(chainService.update(vo.clone(ChainDTO.class)));
    }
//
//    @PostMapping
//    //@ApiOperation(value = "创建CcChain", notes = "创建CcChain")
//    public Payload create(@RequestBody CcChain eo) {
//        return new Payload(chainService.create(eo));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
////@ApiOperation(value = "根据id删除CcChain", notes = "根据id删除CcChain")
//    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
//        return new Payload(chainService.delete(pk));
//    }
//
//    @DeleteMapping
//    @Transactional
//    //@ApiOperation(value = "根据id批量删除CcChain", notes = "根据id批量删除CcChain")
//    public Payload delete(@RequestParam(required = true) Integer [] ids) {
//        return new Payload(chainService.delete(ids));
//    }

}