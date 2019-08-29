//package com.deepexi.channel.controller;
//
//import com.deepexi.util.config.Payload;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import com.deepexi.channel.service.StoreTypeRelationService;
//import com.deepexi.channel.domain.eo.CcStoreTypeRelation;
//import org.springframework.web.bind.annotation.*;
////import io.swagger.annotations.*;
//
//
////@Api(value = "/门店-类型关联表", description = "$desc")
//@RestController
//@RequestMapping("/api/v1/ccStoreTypeRelations")
//public class StoreTypeRelationController {
//
//    @Autowired
//    private StoreTypeRelationService storeTypeRelationService;
//
//
//    @GetMapping
//    //@ApiOperation(value = "分页查询", notes = "分页请求")
//    public  Payload findPage(CcStoreTypeRelation eo,
//                             @RequestParam(value = "page", defaultValue = "0") Integer page,
//                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
//        return new Payload(storeTypeRelationService.findPage(eo, page, size));
//    }
//
//    @GetMapping("/list")
//    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
//    public Payload findAll(CcStoreTypeRelation eo) {
//        return new Payload(storeTypeRelationService.findAll(eo));
//    }
//
//    @GetMapping("/{id}")
//    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
//        return new Payload(storeTypeRelationService.detail(pk));
//    }
//
//
//    @PutMapping("/{id}")
//    @Transactional
////@ApiOperation(value = "根据id修改", notes = "根据id修改CcStoreTypeRelation")
//    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStoreTypeRelation eo) {
//     eo.setId(pk);
//     return new Payload(storeTypeRelationService.update(pk, eo));
//    }
//
//    @PostMapping
//    //@ApiOperation(value = "创建CcStoreTypeRelation", notes = "创建CcStoreTypeRelation")
//    public Payload create(@RequestBody CcStoreTypeRelation eo) {
//        return new Payload(storeTypeRelationService.create(eo));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
////@ApiOperation(value = "根据id删除CcStoreTypeRelation", notes = "根据id删除CcStoreTypeRelation")
//    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
//        return new Payload(storeTypeRelationService.delete(pk));
//    }
//
//    @DeleteMapping
//    @Transactional
//    //@ApiOperation(value = "根据id批量删除CcStoreTypeRelation", notes = "根据id批量删除CcStoreTypeRelation")
//    public Payload delete(@RequestParam(required = true) Integer [] ids) {
//        return new Payload(storeTypeRelationService.delete(ids));
//    }
//
//}