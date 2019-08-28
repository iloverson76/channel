package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.StoreAreaService;
import com.deepexi.channel.domain.eo.CcStoreArea;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/门店区域表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccStoreAreas")
public class StoreAreaController {

    @Autowired
    private StoreAreaService storeAreaService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcStoreArea eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(storeAreaService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcStoreArea eo) {
        return new Payload(storeAreaService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeAreaService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcStoreArea")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStoreArea eo) {
     eo.setId(pk);
     return new Payload(storeAreaService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcStoreArea", notes = "创建CcStoreArea")
    public Payload create(@RequestBody CcStoreArea eo) {
        return new Payload(storeAreaService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcStoreArea", notes = "根据id删除CcStoreArea")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeAreaService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcStoreArea", notes = "根据id批量删除CcStoreArea")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(storeAreaService.delete(ids));
    }

}